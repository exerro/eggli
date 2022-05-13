package me.exerro.eggli

import me.exerro.lifetimes.Lifetime
import org.lwjgl.glfw.GLFW
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.CountDownLatch
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * A [GLWorker] is used to queue OpenGL work to be done. Eggli does all OpenGL
 * operations on a separate thread to avoid common multithreading issues. A
 * [GLWorker] can queue work to be [run later][runLater], and evaluate [GL]
 * objects using either [suspend functions][evaluate] or
 * [thread blocking][evaluateBlocking].
 *
 * A [GLWorker] cannot be created directly, instead falling back on
 * [createWithoutContext] and [createForGLFWWindow], where the latter handles
 * creating the OpenGL context so that OpenGL can immediately be used by the
 * worker. Both these functions expect a [Lifetime] which will stop the worker
 * upon ending.
 */
context (Lifetime)
class GLWorker private constructor(
    threadName: String,
    queueCapacity: Int,
    private val onError: (Throwable) -> Unit,
) {
    /** TODO */
    suspend fun <T> evaluate(
        gl: GL<T>
    ) = suspendCoroutine { cont ->
        runLater {
            cont.resume(gl.evaluate())
        }
    }

    /** TODO */
    fun <T> evaluateBlocking(
        gl: GL<T>
    ): T {
        var value: T? = null
        val lock = CountDownLatch(1)
        runLater {
            value = gl.evaluate()
            lock.countDown()
        }
        lock.await()
        return value!!
    }

    /** TODO */
    suspend fun run() = suspendCoroutine { cont ->
        runLater { cont.resume(Unit) }
    }

    /** TODO */
    fun runLater(
        gl: GL<*>
    ) = runLater { gl.evaluate() }

    /**
     * Run [fn] at *some point* in the future, without waiting for it to be run.
     * Note that execution happens in-order, so if two blocks are submitted to
     * run, the one submitted second will run after the first.
     *
     * @param runIfStopped even if this worker has been stopped, queue [fn] to
     *                     be run (e.g. for calling a destructor)
     */
    fun runLater(
        runIfStopped: Boolean = false,
        fn: context (GLContext) () -> Unit,
    ) {
        if (running || runIfStopped) workQueue.add(fn)
    }

    private var running = true
    private val workQueue = ArrayBlockingQueue<context (GLContext) () -> Unit>(queueCapacity)
    private val context = GLContext(this)
    private val glThread = thread(
        name = threadName,
        start = true,
        isDaemon = false,
    ) {
        while (running || workQueue.isNotEmpty()) {
            try {
                val fn = workQueue.take()
                try { fn(context) }
                catch (e: Throwable) { onError(e) }
            }
            catch (e: InterruptedException) { /* do nothing */ }
        }
    }

    init {
        onLifetimeEnded {
            running = false
            glThread.interrupt()
        }
    }

    /** @see GLWorker */
    companion object {
        /** TODO */
        context (Lifetime)
        fun createWithoutContext(
            threadName: String = "GL Worker Thread",
            queueCapacity: Int = 1024,
            onError: (Throwable) -> Unit = Throwable::printStackTrace,
        ) = GLWorker(threadName, queueCapacity, onError)

        /** TODO */
        context (Lifetime)
        fun createForGLFWWindow(
            glfwWindowId: Long,
            threadName: String = "GL Worker Thread",
            queueCapacity: Int = 1024,
            onError: (Throwable) -> Unit = Throwable::printStackTrace,
        ) = GLWorker(threadName, queueCapacity, onError).also {
            it.runLater {
                GLFW.glfwMakeContextCurrent(glfwWindowId)
                org.lwjgl.opengl.GL.createCapabilities()
            }
        }
    }
}

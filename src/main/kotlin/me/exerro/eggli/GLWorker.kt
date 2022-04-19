package me.exerro.eggli

import me.exerro.lifetimes.Lifetime
import org.lwjgl.glfw.GLFW
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.CountDownLatch
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/** TODO */
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

    /** TODO */
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

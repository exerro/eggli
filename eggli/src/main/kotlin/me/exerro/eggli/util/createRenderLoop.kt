package me.exerro.eggli.util

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLWorker
import org.lwjgl.glfw.GLFW
import java.util.concurrent.CountDownLatch
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Create a "render loop" which continuously runs [renderFrame] on the OpenGL
 * thread by submitting it to [worker]. To avoid blocking other tasks from
 * running on the thread, this function repeatedly queues [renderFrame] to run
 * after each successive render with no delay. Delays may occur when other work
 * is done on the thread between renders.
 *
 * The [RenderLoopHandle] returned can be used to stop the loop from running.
 *
 * @param glfwWindowId window ID used to swap buffers after each render
 */
fun createRenderLoop(
    glfwWindowId: Long,
    worker: GLWorker,
    renderFrame: context (GLContext) () -> Unit
): RenderLoopHandle {
    val isRunning = AtomicBoolean(true)
    val completion = CountDownLatch(1)
    val continuations = mutableListOf<Continuation<Unit>>()

    fun renderFrameQueued(context: GLContext) {
        renderFrame(context)
        GLFW.glfwSwapBuffers(glfwWindowId)
        if (isRunning.get()) worker.runLater(fn = ::renderFrameQueued)
        else {
            synchronized(completion) {
                completion.countDown()
            }
        }
    }

    worker.runLater(fn = ::renderFrameQueued)

    return object: RenderLoopHandle {
        override val isRunning get() = isRunning.get()

        override suspend fun stop() {
            isRunning.set(false)

            suspendCoroutine { cont ->
                synchronized(completion) {
                    if (completion.count > 0L)
                        continuations.add(cont)
                    else
                        cont.resume(Unit)
                }
            }
        }

        override fun stopLater() {
            isRunning.set(false)
        }

        override fun stopBlocking() {
            isRunning.set(false)
            completion.await()
        }
    }
}

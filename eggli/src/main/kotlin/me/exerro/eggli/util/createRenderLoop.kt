package me.exerro.eggli.util

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLWorker
import org.lwjgl.glfw.GLFW

/** TODO */
fun createRenderLoop(
    glfwWindowId: Long,
    worker: GLWorker,
    fn: context (GLContext) () -> Boolean
) {
    fun f(context: GLContext) {
        val keepRunning = fn(context)
        GLFW.glfwSwapBuffers(glfwWindowId)
        if (keepRunning) worker.runLater(fn = ::f)
    }
    worker.runLater(fn = ::f)
}

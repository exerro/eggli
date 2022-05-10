package me.exerro.eggli.util

import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.*
import me.exerro.eggli.GLDebugger.LogEntity.*
import me.exerro.eggli.GLWorker
import me.exerro.lifetimes.Lifetime
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GLUtil
import org.lwjgl.system.Configuration
import org.lwjgl.system.MemoryUtil.NULL

/** TODO */
context (Lifetime, GLDebugger.Context)
fun createGLFWWindow(
    width: Int = 1080,
    height: Int = 720,
    title: CharSequence = "Untitled Window",
    monitor: Long = NULL,
    share: Long = NULL,
    debug: Boolean = true,
): Long {
    val windowId = GLFW.glfwCreateWindow(width, height, title, monitor, share)
    assert(windowId != NULL) { "Failed to create GLFW window. Make sure glfwInit has been called!" }
    glLog(ObjectCreated, Window, "Created window $windowId ('$title')")

    if (debug) {
        GLFWErrorCallback.createPrint().set()
        Configuration.DEBUG.set(true)
    }

    GLFW.glfwMakeContextCurrent(NULL)
    onLifetimeEnded {
        GLFW.glfwDestroyWindow(windowId)
        glLog(ObjectDestroyed, Window, "Destroyed window $windowId")
    }
    return windowId
}

/** TODO */
context (Lifetime, GLDebugger.Context)
fun createGLFWWindowWithWorker(
    width: Int = 1080,
    height: Int = 720,
    title: CharSequence = "Untitled Window",
    monitor: Long = NULL,
    share: Long = NULL,
    debug: Boolean = true,
): Pair<Long, GLWorker> {
    val windowId = createGLFWWindow(width = width, height = height,
        title = title, monitor = monitor, share = share, debug = debug)
    val worker = GLWorker.createForGLFWWindow(windowId)
    if (debug) worker.runLater {
        GLUtil.setupDebugMessageCallback()
    }
    return windowId to worker
}

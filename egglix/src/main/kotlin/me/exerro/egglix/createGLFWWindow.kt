package me.exerro.egglix

import me.exerro.eggli.GLWorker
import me.exerro.lifetimes.Lifetime
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GLUtil
import org.lwjgl.system.Configuration
import org.lwjgl.system.MemoryUtil.NULL

/**
 * Create a GLFW window, returning the native GLFW window id. The window is set
 * up for verbose debugging and is not fullscreen on any monitor. When the
 * surrounding lifetime ends, the window will be destroyed.
 */
context (Lifetime)
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

    if (debug) {
        GLFWErrorCallback.createPrint().set()
        Configuration.DEBUG.set(true)
    }

    GLFW.glfwMakeContextCurrent(NULL)
    onLifetimeEnded {
        GLFW.glfwDestroyWindow(windowId)
    }
    return windowId
}

/**
 * Create a GLFW window and a [GLWorker]. The native GLFW window id is returned,
 * along with the worker. An OpenGL rendering thread is created in the
 * background which the returned [GLWorker] uses. Both the window and the worker
 * are set up for verbose debugging. When the surrounding lifetime ends, the
 * window will be destroyed.
 *
 * @see createGLFWWindow
 */
context (Lifetime)
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

package me.exerro.eggli

import me.exerro.eggli.gl.glGenTextures
import me.exerro.eggli.util.createGLFWWindowWithWorker
import me.exerro.eggli.util.createRenderLoop
import me.exerro.lifetimes.Lifetime
import me.exerro.lifetimes.withLifetime
import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL46C

class GLObjects(

)

context (Lifetime, GLDebugger)
fun createRenderingObjects() = GL {
    val (texture) = glGenTextures()
    GLObjects()
}

context (GLContext, GLDebugger)
fun renderFrame(glObjects: GLObjects) {
    GL46C.glClearColor(0.1f, 0.12f, 0.13f, 1f)
    GL46C.glClear(GL46C.GL_COLOR_BUFFER_BIT)
}

context (Lifetime, GLDebugger)
fun runWindow() {
    val (windowId, worker) = createGLFWWindowWithWorker(title = "Eggli Test Window")
    val glObjects = worker.evaluateBlocking(createRenderingObjects())

    createRenderLoop(windowId, worker) {
        renderFrame(glObjects)
        isAlive
    }

    while (!GLFW.glfwWindowShouldClose(windowId)) {
        GLFW.glfwWaitEvents()
    }
}

fun main() {
    GLFW.glfwInit()

    val debugger = GLDebugger
        .createDefault()

    withLifetime {
        with(debugger) {
            runWindow()
        }
    }
}

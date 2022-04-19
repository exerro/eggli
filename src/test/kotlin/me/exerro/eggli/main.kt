package me.exerro.eggli

import me.exerro.eggli.enum.GLBufferTarget
import me.exerro.eggli.enum.GLShaderType
import me.exerro.eggli.enum.GLType
import me.exerro.eggli.gl.*
import me.exerro.eggli.types.GLShaderProgram
import me.exerro.eggli.types.GLVertexArray
import me.exerro.eggli.util.createGLFWWindowWithWorker
import me.exerro.eggli.util.createRenderLoop
import me.exerro.eggli.util.withDebugContext
import me.exerro.lifetimes.Lifetime
import me.exerro.lifetimes.withLifetime
import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL46C

class GLObjects(
    val shaderProgram: GLShaderProgram,
    val vertexArray: GLVertexArray
)

const val VERTEX_SHADER = """
#version 460 core

layout (location = 0) in vec3 v_position;

out vec3 f_position;
out vec3 f_colour;

void main() {
    gl_Position = vec4(v_position, 1);
    f_position = v_position;
    f_colour = v_position;
}
"""

const val FRAGMENT_SHADER = """
#version 460 core

in vec3 f_position;
in vec3 f_colour;

out vec4 o_colour;

void main() {
    o_colour = vec4(f_colour, 1);
}
"""

context (Lifetime, GLDebugger.Context)
fun createRenderingObjects() = GL {
    val (shaderProgram) = glCreateProgram()
    val (vao) = glGenVertexArrays()
    val (positionBuffer) = glGenBuffers()

    glNamedBufferData(positionBuffer, floatArrayOf(
        -0.5f, -0.5f, 0.0f,
        0.5f, -0.5f, 0.0f,
        0.0f, 0.5f, 0.0f,
    ))

    glBindVertexArray(vao) {
        glBindBuffer(GLBufferTarget.Array, positionBuffer) {
            glVertexAttribPointer(0, 3, GLType.Float)
        }
    }

    glEnableVertexAttribArray(vao, 0)

    withLifetime {
        val (vertex) = glCreateShader(GLShaderType.Vertex)
        val (fragment) = glCreateShader(GLShaderType.Fragment)

        glShaderSource(vertex, VERTEX_SHADER)
        glShaderSource(fragment, FRAGMENT_SHADER)
        glCompileShader(vertex)
        glCompileShader(fragment)
        glAttachShader(shaderProgram, vertex)
        glAttachShader(shaderProgram, fragment)
        glLinkProgram(shaderProgram)
        glDetachShader(shaderProgram, vertex)
        glDetachShader(shaderProgram, fragment)
    }

    GLObjects(
        shaderProgram = shaderProgram,
        vertexArray = vao,
    )
}

context (GLContext, GLDebugger.Context)
fun renderFrame(glObjects: GLObjects) {
    GL46C.glClearColor(0.1f, 0.12f, 0.13f, 1f)
    GL46C.glClear(GL46C.GL_COLOR_BUFFER_BIT)

    glUseProgram(glObjects.shaderProgram) {
        glBindVertexArray(glObjects.vertexArray) {
            GL46C.glDrawArrays(GL46C.GL_TRIANGLES, 0, 3)
        }
    }
}

context (Lifetime, GLDebugger.Context)
fun runWindow() {
    val (windowId, worker) = createGLFWWindowWithWorker(title = "Eggli Test Window")
    val glObjects = worker.evaluateBlocking(createRenderingObjects())
    val renderingDebugger = glDebugger
        .ignoringAction(GLDebugger.LogAction.ObjectBound)
        .ignoringAction(GLDebugger.LogAction.ObjectUnbound)

    withDebugContext(renderingDebugger) {
        createRenderLoop(windowId, worker) {
            renderFrame(glObjects)
            isAlive
        }
    }

    while (!GLFW.glfwWindowShouldClose(windowId)) {
        GLFW.glfwWaitEvents()
    }
}

fun main() {
    GLFW.glfwInit()

    val debugger = GLDebugger
        .createDefault()
        .ansiColoured()

    withLifetime {
        withDebugContext(debugger) {
            runWindow()
        }
    }
}

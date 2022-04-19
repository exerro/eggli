package me.exerro.eggli

import me.exerro.eggli.GLDebugger.LogAction.*
import me.exerro.eggli.enum.*
import me.exerro.eggli.gl.*
import me.exerro.eggli.types.GLShaderProgram
import me.exerro.eggli.types.GLVertexArray
import me.exerro.eggli.util.*
import me.exerro.lifetimes.Lifetime
import me.exerro.lifetimes.withLifetime
import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL46C

class GLObjects(
    val shaderProgram: GLShaderProgram,
    val vertexArray: GLVertexArray,
    val vertices: Int,
)

const val VERTEX_SHADER_SOURCE = """
#version 460 core

uniform mat4 u_projectionMatrix;

layout (location = 0) in vec3 v_position;
layout (location = 3) in vec3 v_colour;

out vec3 f_position;
out vec3 f_colour;

void main() {
    gl_Position = u_projectionMatrix * vec4(v_position, 1);
    f_position = v_position;
    f_colour = v_colour;
}
"""

const val FRAGMENT_SHADER_SOURCE = """
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
    val (shaderProgram) = createShaderProgram(
        GL_VERTEX_SHADER to VERTEX_SHADER_SOURCE,
        GL_FRAGMENT_SHADER to FRAGMENT_SHADER_SOURCE,
    )
    val (cube) = createDefaultCube(
        includeUVs = false,
        includeColours = true,
        useElements = true,
    )
    val proj = createPerspectiveProjectionMatrixValues(aspectRatio = 1080f / 720f)

//    glEnable(GLTarget.CullFace)
    glEnable(GLTarget.DepthTest)

    glUseProgram(shaderProgram) {
        glUniformMatrix4fv(glGetUniformLocation(shaderProgram, "u_projectionMatrix"), proj)
    }

    GLObjects(
        shaderProgram = shaderProgram,
        vertexArray = cube.get().vertexArray,
        vertices = DefaultCubeObjects.VERTICES,
    )
}

context (GLContext, GLDebugger.Context)
fun renderFrame(glObjects: GLObjects) {
    glClearColor(0.1f, 0.12f, 0.13f)
    glClear(GL_COLOR_BUFFER_BIT + GL_DEPTH_BUFFER_BIT)

    glUseProgram(glObjects.shaderProgram) {
        glBindVertexArray(glObjects.vertexArray) {
//            glDrawArrays(GL_TRIANGLES, 0, glObjects.vertices)
            glDrawElements(GL_TRIANGLES, glObjects.vertices)
        }
    }
}

context (Lifetime, GLDebugger.Context)
fun runWindow() {
    val (windowId, worker) = createGLFWWindowWithWorker(title = "Eggli Test Window")
    val glObjects = worker.evaluateBlocking(createRenderingObjects())
    val renderingDebugger = glDebugger
        .ignoringAction(ObjectBound)
        .ignoringAction(ObjectUnbound)
        .ignoringAction(DrawCall)
        .ignoringAction(StateChanged)

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

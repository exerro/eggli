package me.exerro.eggli

import me.exerro.eggli.GLDebugger.LogAction.*
import me.exerro.eggli.GLDebugger.LogEntity.Shader
import me.exerro.eggli.enum.*
import me.exerro.eggli.gl.*
import me.exerro.eggli.types.GLShaderProgram
import me.exerro.eggli.types.GLTexture
import me.exerro.eggli.util.*
import me.exerro.lifetimes.Lifetime
import me.exerro.lifetimes.withLifetime
import org.lwjgl.glfw.GLFW

class GLObjects(
    val texture: GLTexture,
    val shaderProgram: GLShaderProgram,
    val shape: GLResource<SimpleMesh>,
)

const val VERTEX_SHADER_SOURCE = """
#version 460 core

uniform mat4 u_projectionMatrix;

layout (location = 0) in vec3 v_position;
layout (location = 1) in vec2 v_uv;
layout (location = 3) in vec3 v_colour;

out vec3 f_position;
out vec2 f_uv;
out vec3 f_colour;

void main() {
    gl_Position = u_projectionMatrix * vec4(v_position, 1);
    f_position = v_position;
    f_uv = v_uv;
    f_colour = v_colour;
}
"""

const val FRAGMENT_SHADER_SOURCE = """
#version 460 core

uniform float u_time;
uniform sampler2D u_texture;

in vec3 f_position;
in vec2 f_uv;
in vec3 f_colour;

out vec4 o_colour;

void main() {
    o_colour = vec4(f_colour * (1 + cos(u_time)) / 2, 1) * texture(u_texture, f_uv);
//    o_colour = texture(u_texture, f_uv);
}
"""

context (Lifetime, GLDebugger.Context)
fun createRenderingObjects() = GL {
    val useElements = false
    val (shaderProgram) = createShaderProgram(
        GL_VERTEX_SHADER to VERTEX_SHADER_SOURCE,
        GL_FRAGMENT_SHADER to FRAGMENT_SHADER_SOURCE,
    )
    val (shape) = createDefaultCube(
        includeUVs = true,
        includeColours = true,
        useElements = useElements,
        uvsPerFace = true,
    )
//    val (shape) = createDefaultFace(
//        includeUVs = true,
//        includeColours = true,
//        useElements = useElements,
//    )
    val proj = createPerspectiveProjectionMatrixValues(aspectRatio = 1080f / 720f)
    val textureData = GL::class.java.getResourceAsStream("/me/exerro/eggli/img/box_0_diffuse.png")!!.readBytes()
    val (texture) = glCreateTextures(GL_TEXTURE_2D)
    glTextureParameter(texture, GL_TEXTURE_MIN_FILTER, GL_LINEAR)
    glTextureParameter(texture, GL_TEXTURE_MAG_FILTER, GL_LINEAR)
    glTextureParameter(texture, GL_TEXTURE_MAG_FILTER, GL_NEAREST)
    loadTextureData(texture, textureData)
    glTextureParameter(texture, GL_TEXTURE_MAG_FILTER, GL_LINEAR)

    glEnable(GLOption.CullFace)
    glEnable(GLOption.DepthTest)
    glCullFace(GL_FRONT)

    glUseProgram(shaderProgram) {
        glUniformMatrix4fv(glGetUniformLocation(shaderProgram, "u_projectionMatrix"), proj)
    }

    GLObjects(
        texture = texture,
        shaderProgram = shaderProgram,
        shape = shape,
    )
}

context (GLContext, GLDebugger.Context)
fun renderFrame(glObjects: GLObjects, t: Float) {
    glClearColor(0.1f, 0.12f, 0.13f)
    glClear(GL_COLOR_BUFFER_BIT + GL_DEPTH_BUFFER_BIT)

    glUseProgram(glObjects.shaderProgram) {
        glUniform1f(glGetUniformLocation(glObjects.shaderProgram, "u_time"), t)
        glBindTexture(GL_TEXTURE_2D, glObjects.texture) {
            glBindVertexArray(glObjects.shape.get().vertexArray) {
                if (glObjects.shape.get().usesElementBuffer)
                    glDrawElements(count = glObjects.shape.get().vertices)
                else
                    glDrawArrays(count = glObjects.shape.get().vertices)
            }
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
        .ignoring(Info, Shader)
        .ignoring(UniformChanged, Shader)
    val t0 = System.currentTimeMillis()
    val renderLoopHandle = withDebugContext(renderingDebugger) {
        createRenderLoop(windowId, worker) {
            renderFrame(glObjects, (System.currentTimeMillis() - t0) / 1000f)
        }
    }

    while (!GLFW.glfwWindowShouldClose(windowId)) {
        GLFW.glfwWaitEvents()
    }

    renderLoopHandle.stopBlocking()
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

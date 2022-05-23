package me.exerro.eggli

import me.exerro.eggli.enum.*
import me.exerro.eggli.gl.*
import me.exerro.eggli.types.GLFramebuffer
import me.exerro.eggli.types.GLShaderProgram
import me.exerro.eggli.types.GLTexture
import me.exerro.eggli.util.*
import me.exerro.lifetimes.Lifetime
import me.exerro.lifetimes.withLifetime
import org.lwjgl.glfw.GLFW

const val FRAMEBUFFER_WIDTH = 800
const val FRAMEBUFFER_HEIGHT = 600

const val WINDOW_WIDTH = 1080
const val WINDOW_HEIGHT = 720

class GLObjects(
    val framebuffer: GLFramebuffer,
    val boxTexture: GLTexture,
    val albedoTexture: GLTexture,
    val positionTexture: GLTexture,
    val normalTexture: GLTexture,
    val depthTexture: GLTexture,
    val modelShaderProgram: GLShaderProgram,
    val aoShaderProgram: GLShaderProgram,
    val shape: GLResource<SimpleMesh>,
    val fullscreenQuad: GLResource<SimpleMesh>,
)

const val FRAGMENT_SHADER_SOURCE = """
#version 460 core

uniform float u_time;
uniform sampler2D u_texture;

in vec3 f_position;
in vec2 f_uv;
in vec3 f_normal;
in vec4 f_colour;

layout (location = 0) out vec4 o_colour;
layout (location = 1) out vec4 o_position;
layout (location = 2) out vec4 o_normal;

void main() {
    o_colour = vec4(f_colour.xyz, (1 + cos(u_time)) / 2) * texture(u_texture, f_uv);
    o_position.xyz = f_position;
    o_normal.xyz = f_normal.xyz;
    o_normal.w = 1;
    o_normal = vec4(f_colour.xyz, (1 + cos(u_time)) / 2) * texture(u_texture, f_uv);
//    o_colour = texture(u_texture, f_uv);
}
"""

const val AO_FRAGMENT_SHADER_SOURCE = """
#version 460 core

uniform float u_time;
layout (binding = 0) uniform sampler2D u_texture;

in vec2 f_uv;

out vec4 o_colour;

void main() {
//    o_colour = vec4(f_uv, 0, 1);
    o_colour.xyz = texture(u_texture, f_uv).xyz;
    o_colour.w = 1;
}
"""

context (Lifetime)
fun createRenderingObjects() = GL {
    val useElements = false
    val (shaderProgram) = createShaderProgram(
        createModelVertexShaderSource(
            positionLocation = 0,
            normalLocation = 2,
            ShaderAttribute.uv,
            ShaderAttribute.colour,
        ),
//        VERTEX_SHADER_SOURCE,
        FRAGMENT_SHADER_SOURCE,
    )
    val (aoShaderProgram) = createShaderProgram(
        createPassThroughVertexShaderSource(
            positionLocation = 0,
            ShaderAttribute.uv,
        ),
        AO_FRAGMENT_SHADER_SOURCE,
    )
    val (shape) = createDefaultCube(
        includeUVs = true,
        includeColours = true,
        useElements = useElements,
        uvsPerFace = true,
        centreZ = 0f,
    )
    val (fullscreenQuad) = createDefaultFace(
        includeNormals = false,
        useElements = false,
        width = 2f,
        height = 2f,
    )
    val (framebuffer) = glGenFramebuffers()
    val proj = createPerspectiveProjectionMatrixValues(aspectRatio = 1080f / 720f)
    val textureData = GL::class.java.getResourceAsStream("/me/exerro/eggli/img/box_0_diffuse.png")!!.readBytes()

    val (boxTexture) = glCreateTextures(GL_TEXTURE_2D)
    glTextureParameter(boxTexture, GL_TEXTURE_MIN_FILTER, GL_LINEAR)
    glTextureParameter(boxTexture, GL_TEXTURE_MAG_FILTER, GL_LINEAR)
    loadTextureData(boxTexture, textureData)

    val (albedoTexture) = glCreateTextures(GL_TEXTURE_2D)
    glTextureParameter(albedoTexture, GL_TEXTURE_MIN_FILTER, GL_LINEAR)
    glTextureParameter(albedoTexture, GL_TEXTURE_MAG_FILTER, GL_LINEAR)
    glTextureStorage2D(albedoTexture, width = FRAMEBUFFER_WIDTH, height = FRAMEBUFFER_HEIGHT)

    val (positionTexture) = glCreateTextures(GL_TEXTURE_2D)
    glTextureParameter(positionTexture, GL_TEXTURE_MIN_FILTER, GL_LINEAR)
    glTextureParameter(positionTexture, GL_TEXTURE_MAG_FILTER, GL_LINEAR)
    glTextureStorage2D(positionTexture, internalFormat = GL_RGB32F, width = FRAMEBUFFER_WIDTH, height = FRAMEBUFFER_HEIGHT)

    val (normalTexture) = glCreateTextures(GL_TEXTURE_2D)
    glTextureParameter(normalTexture, GL_TEXTURE_MIN_FILTER, GL_LINEAR)
    glTextureParameter(normalTexture, GL_TEXTURE_MAG_FILTER, GL_LINEAR)
    glTextureStorage2D(normalTexture, internalFormat = GL_RGBA8, width = FRAMEBUFFER_WIDTH, height = FRAMEBUFFER_HEIGHT)

    val (depthTexture) = glCreateTextures(GL_TEXTURE_2D)
    glTextureParameter(depthTexture, GL_TEXTURE_MIN_FILTER, GL_LINEAR)
    glTextureParameter(depthTexture, GL_TEXTURE_MAG_FILTER, GL_LINEAR)
    glTextureStorage2D(depthTexture, internalFormat = GL_DEPTH_COMPONENT24, width = FRAMEBUFFER_WIDTH, height = FRAMEBUFFER_HEIGHT)

    glBindFramebuffer(GL_FRAMEBUFFER, framebuffer) {
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, albedoTexture)
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT1, GL_TEXTURE_2D, positionTexture)
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT2, GL_TEXTURE_2D, normalTexture)
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT, GL_TEXTURE_2D, depthTexture)

        if (glCheckFramebufferStatus(GL_FRAMEBUFFER) != GL_FRAMEBUFFER_COMPLETE)
            error("Fuck")
    }

    glEnable(GL_CULL_FACE)
    glEnable(GL_DEPTH_TEST)
    glEnable(GL_BLEND)
    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA)

    glUseProgram(shaderProgram) {
        glUniformMatrix4fv(glGetUniformLocation(shaderProgram, "u_projectionMatrix"), proj)
        glUniformMatrix4fv(glGetUniformLocation(shaderProgram, "u_viewMatrix"), createIdentityMatrixValues())
        glUniformMatrix4fv(glGetUniformLocation(shaderProgram, "u_modelMatrix"), createIdentityMatrixValues())
    }

    GLObjects(
        framebuffer = framebuffer,
        boxTexture = boxTexture,
        albedoTexture = albedoTexture,
        positionTexture = positionTexture,
        normalTexture = normalTexture,
        depthTexture = depthTexture,
        modelShaderProgram = shaderProgram,
        aoShaderProgram = aoShaderProgram,
        shape = shape,
        fullscreenQuad = fullscreenQuad,
    )
}

context (GLContext)
fun renderFrame(glObjects: GLObjects, t: Float) {
    glUseProgram(glObjects.modelShaderProgram) {
        val rotation = createYRotationMatrixValues(t)
        val translation = createTranslationMatrixValues(dz = -1f)
        val transform = multiplyMatrixValues(translation, rotation)
        glUniformMatrix4fv(glGetUniformLocation(glObjects.modelShaderProgram, "u_modelMatrix"), transform)
    }

    glBindFramebuffer(GL_DRAW_FRAMEBUFFER, glObjects.framebuffer) {
        glViewport(0, 0, FRAMEBUFFER_WIDTH, FRAMEBUFFER_HEIGHT)
        glClearColor(1f, 1f, 1f, 1f)
        glClear(GL_COLOR_BUFFER_BIT + GL_DEPTH_BUFFER_BIT)
        glCullFace(GL_FRONT)
        glUseProgram(glObjects.modelShaderProgram) {
            glUniform1f(glGetUniformLocation(glObjects.modelShaderProgram, "u_time"), t)
            glBindTexture(GL_TEXTURE_2D, glObjects.boxTexture) {
                glBindVertexArray(glObjects.shape.get().vertexArray) {
                    if (glObjects.shape.get().usesElementBuffer)
                        glDrawElements(count = glObjects.shape.get().vertices)
                    else
                        glDrawArrays(count = glObjects.shape.get().vertices)
                }
            }
        }
    }

    glViewport(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT)
    glClearColor(0.1f, 0.12f, 0.13f)
    glClear(GL_COLOR_BUFFER_BIT + GL_DEPTH_BUFFER_BIT)
    glCullFace(GL_BACK)
    glUseProgram(glObjects.aoShaderProgram) {
        glBindTexture(GL_TEXTURE_2D, glObjects.normalTexture) {
            glBindVertexArray(glObjects.fullscreenQuad.get().vertexArray) {
                if (glObjects.fullscreenQuad.get().usesElementBuffer)
                    glDrawElements(count = glObjects.fullscreenQuad.get().vertices)
                else
                    glDrawArrays(count = glObjects.fullscreenQuad.get().vertices)
            }
        }
    }
}

context (Lifetime)
fun runWindow() {
    val (windowId, worker) = createGLFWWindowWithWorker(
        width = WINDOW_WIDTH,
        height = WINDOW_HEIGHT,
        title = "Eggli Test Window",
    )
    val glObjects = worker.evaluateBlocking(createRenderingObjects())
    val t0 = System.currentTimeMillis()
    val renderLoopHandle = createRenderLoop(windowId, worker) {
        renderFrame(glObjects, (System.currentTimeMillis() - t0) / 1000f)
    }

    while (!GLFW.glfwWindowShouldClose(windowId)) {
        GLFW.glfwWaitEvents()
    }

    renderLoopHandle.stopBlocking()
}

fun main() {
    GLFW.glfwInit()

    withLifetime {
        runWindow()
    }
}

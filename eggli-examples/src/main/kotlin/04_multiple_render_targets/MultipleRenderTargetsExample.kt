package `04_multiple_render_targets`

import BaseExample
import WINDOW_HEIGHT
import WINDOW_WIDTH
import me.exerro.eggli.GL
import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.enum.*
import me.exerro.eggli.gl.*
import me.exerro.eggli.util.*
import me.exerro.lifetimes.Lifetime
import org.lwjgl.opengl.GL46C

/**
 * TODO
 */
class MultipleRenderTargetsExample: BaseExample<MultipleRenderTargetsExampleData>() {
    private val FRAMEBUFFER_WIDTH = 800
    private val FRAMEBUFFER_HEIGHT = 600

    context (Lifetime, GLDebugger.Context)
    override fun createData(): GL<MultipleRenderTargetsExampleData> = GL {
        val (modelTexture) = createDebugTexture(divisions = 3, c0 = 0xfafafa, c1 = 0x444444)
        val projectionMatrix = createPerspectiveProjectionMatrixValues(
            fov = 1.5f,
            aspectRatio = WINDOW_WIDTH.toFloat() / WINDOW_HEIGHT,
            far = 5f
        )
        val (modelShaderProgram) = createShaderProgram(
            createModelVertexShaderSource(
                positionLocation = SimpleMesh.POSITION_ATTRIBUTE,
                normalLocation = SimpleMesh.NORMAL_ATTRIBUTE,
                ShaderAttribute.uv,
                ShaderAttribute.colour,
            ),
            MODEL_FRAGMENT_SHADER_SOURCE,
        )
        val (screenShaderProgram) = createShaderProgram(
            createPassThroughVertexShaderSource(
                positionLocation = SimpleMesh.POSITION_ATTRIBUTE,
                ShaderAttribute.uv,
            ),
            SCREEN_FRAGMENT_SHADER_SOURCE,
        )
        val (modelMesh) = createDefaultCube(includeColours = true, width = 2f, height = 2f, depth = 2f, centreZ = 0f, centreY = -1.5f, uvsPerFace = true)
        val (topLeftQuad) = createDefaultFace(useElements = false, includeNormals = false, centreX = -0.5f, centreY = 0.5f, height = -1f)
        val (topRightQuad) = createDefaultFace(useElements = false, includeNormals = false, centreX = 0.5f, centreY = 0.5f, height = -1f)
        val (bottomLeftQuad) = createDefaultFace(useElements = false, includeNormals = false, centreX = -0.5f, centreY = -0.5f, height = -1f)
        val (bottomRightQuad) = createDefaultFace(useElements = false, includeNormals = false, centreX = 0.5f, centreY = -0.5f, height = -1f)
        val projectionLocation = glGetUniformLocation(modelShaderProgram, "u_projectionMatrix")
        val viewLocation = glGetUniformLocation(modelShaderProgram, "u_viewMatrix")
        val modelLocation = glGetUniformLocation(modelShaderProgram, "u_modelMatrix")
        val (framebuffer) = glGenFramebuffers()

        val (albedoTexture) = glCreateTextures(GL_TEXTURE_2D)
        glTextureParameter(albedoTexture, GL_TEXTURE_MIN_FILTER, GL_LINEAR)
        glTextureParameter(albedoTexture, GL_TEXTURE_MAG_FILTER, GL_LINEAR)
        glTextureStorage2D(albedoTexture, internalFormat = GL_RGBA8, width = FRAMEBUFFER_WIDTH, height = FRAMEBUFFER_HEIGHT)

        val (positionTexture) = glCreateTextures(GL_TEXTURE_2D)
        glTextureParameter(positionTexture, GL_TEXTURE_MIN_FILTER, GL_LINEAR)
        glTextureParameter(positionTexture, GL_TEXTURE_MAG_FILTER, GL_LINEAR)
        glTextureStorage2D(positionTexture, internalFormat = GL_RGB16F, width = FRAMEBUFFER_WIDTH, height = FRAMEBUFFER_HEIGHT)

        val (normalTexture) = glCreateTextures(GL_TEXTURE_2D)
        glTextureParameter(normalTexture, GL_TEXTURE_MIN_FILTER, GL_LINEAR)
        glTextureParameter(normalTexture, GL_TEXTURE_MAG_FILTER, GL_LINEAR)
        glTextureStorage2D(normalTexture, internalFormat = GL_RGB32F, width = FRAMEBUFFER_WIDTH, height = FRAMEBUFFER_HEIGHT)

        val (depthTexture) = glCreateTextures(GL_TEXTURE_2D)
        glTextureParameter(depthTexture, GL_TEXTURE_MIN_FILTER, GL_LINEAR)
        glTextureParameter(depthTexture, GL_TEXTURE_MAG_FILTER, GL_LINEAR)
        glTextureStorage2D(depthTexture, internalFormat = GL_DEPTH_COMPONENT24, width = FRAMEBUFFER_WIDTH, height = FRAMEBUFFER_HEIGHT)

        glUseProgram(modelShaderProgram) {
            glUniformMatrix4fv(projectionLocation, projectionMatrix)
            glUniformMatrix4fv(viewLocation, createIdentityMatrixValues())
        }

        glBindFramebuffer(GL_FRAMEBUFFER, framebuffer) {
            glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, albedoTexture)
            glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT1, GL_TEXTURE_2D, positionTexture)
            glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT2, GL_TEXTURE_2D, normalTexture)
            glFramebufferTexture2D(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT, GL_TEXTURE_2D, depthTexture)

            if (glCheckFramebufferStatus(GL_FRAMEBUFFER) != GL_FRAMEBUFFER_COMPLETE)
                error("Incomplete framebuffer")
        }
        glNamedFramebufferDrawBuffers(framebuffer, GL_COLOR_ATTACHMENT0, GL_COLOR_ATTACHMENT1, GL_COLOR_ATTACHMENT2)

        MultipleRenderTargetsExampleData(
            modelShaderProgram = modelShaderProgram,
            screenShaderProgram = screenShaderProgram,
            modelMatrixUniformLocation = modelLocation,
            modelMesh = modelMesh,
            topLeftQuad = topLeftQuad,
            topRightQuad = topRightQuad,
            bottomLeftQuad = bottomLeftQuad,
            bottomRightQuad = bottomRightQuad,
            framebuffer = framebuffer,
            modelTexture = modelTexture,
            albedoTexture = albedoTexture,
            positionTexture = positionTexture,
            normalTexture = normalTexture,
            depthTexture = depthTexture,
        )
    }

    context (GLContext, GLDebugger.Context)
    override fun renderFrame(data: MultipleRenderTargetsExampleData, time: Float) {
        val mesh = data.modelMesh.get()
        val topLeftQuad = data.topLeftQuad.get()
        val topRightQuad = data.topRightQuad.get()
        val bottomLeftQuad = data.bottomLeftQuad.get()
        val bottomRightQuad = data.bottomRightQuad.get()
        val rotation = createYRotationMatrixValues(time)
        val translation = createTranslationMatrixValues(dz = -2f)
        val transform = multiplyMatrixValues(translation, rotation)

        glBindFramebuffer(GL_FRAMEBUFFER, data.framebuffer) {
            glViewport(w = FRAMEBUFFER_WIDTH, h = FRAMEBUFFER_HEIGHT)
            glClearColor(0f, 0f, 0f)
            GL46C.glClearDepthf(1f)
            glEnable(GL_DEPTH_TEST)
            glClear(GL_COLOR_BUFFER_BIT + GL_DEPTH_BUFFER_BIT)

            glUseProgram(data.modelShaderProgram) {
                glUniformMatrix4fv(data.modelMatrixUniformLocation, transform)

                glBindTexture(GL_TEXTURE_2D, data.modelTexture) {
                    glBindVertexArray(mesh.vertexArray) {
                        if (mesh.usesElementBuffer)
                            glDrawElements(count = mesh.vertices)
                        else
                            glDrawArrays(count = mesh.vertices)
                    }
                }
            }
        }

        glViewport(w = WINDOW_WIDTH, h = WINDOW_HEIGHT)
        glClearColor(0.1f, 0.12f, 0.13f)
        glDisable(GL_DEPTH_TEST)
        glClear(GL_COLOR_BUFFER_BIT)

        glUseProgram(data.screenShaderProgram) {
            glBindTexture(GL_TEXTURE_2D, data.albedoTexture) {
                glBindVertexArray(topLeftQuad.vertexArray) {
                    glDrawArrays(count = topLeftQuad.vertices)
                }
            }

            glBindTexture(GL_TEXTURE_2D, data.positionTexture) {
                glBindVertexArray(topRightQuad.vertexArray) {
                    glDrawArrays(count = topRightQuad.vertices)
                }
            }

            glBindTexture(GL_TEXTURE_2D, data.normalTexture) {
                glBindVertexArray(bottomLeftQuad.vertexArray) {
                    glDrawArrays(count = bottomLeftQuad.vertices)
                }
            }

            glBindTexture(GL_TEXTURE_2D, data.depthTexture) {
                glBindVertexArray(bottomRightQuad.vertexArray) {
                    glDrawArrays(count = bottomRightQuad.vertices)
                }
            }
        }
    }
}

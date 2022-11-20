package `06_ssao`

import BaseExample
import WINDOW_HEIGHT
import WINDOW_WIDTH
import me.exerro.eggli.GL
import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.*
import me.exerro.eggli.gl.*
import me.exerro.egglix.math.*
import me.exerro.egglix.mesh.SimpleMesh
import me.exerro.egglix.mesh.createDefaultCube
import me.exerro.egglix.postprocessing.SSAO
import me.exerro.egglix.postprocessing.FullscreenQuad
import me.exerro.egglix.shader.ShaderAttribute
import me.exerro.egglix.shader.createModelVertexShaderSource
import me.exerro.egglix.shader.createShaderProgram
import me.exerro.lifetimes.Lifetime
import org.lwjgl.opengl.GL46C

/**
 * Renders raw (un-blurred, native resolution) SSAO for a scene.
 */
class SSAOExample: BaseExample<SSAOExampleData>() {
    context (Lifetime)
    override fun createData(): GL<SSAOExampleData> = GL {
        val projectionMatrix = createPerspectiveProjectionMatrixValues(
            fov = 1.5f,
            aspectRatio = WINDOW_WIDTH.toFloat() / WINDOW_HEIGHT,
            near = 0.01f,
            far = 1000f,
        )
        val viewMatrix = multiplyMatrixValues(
            createXRotationMatrixValues(theta = 0.4f),
            createYRotationMatrixValues(theta = -0.4f),
            createTranslationMatrixValues(dy = -2f)
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
        val (framebuffer) = glGenFramebuffers()
        val quad = FullscreenQuad.create()
        val projectionLocation = glGetUniformLocation(modelShaderProgram, "u_projectionMatrix")
        val viewLocation = glGetUniformLocation(modelShaderProgram, "u_viewMatrix")
        val modelLocation = glGetUniformLocation(modelShaderProgram, "u_modelMatrix")
        val ssao = SSAO.create()

        val objects = (1 .. 50).map {
            val x = Math.random().toFloat() * 30 - 25
            val z = -Math.random().toFloat() * 20
            val w = Math.random().toFloat() + 1
            val d = Math.random().toFloat() + 1
            val (mesh) = createDefaultCube(width = w, depth = d, centreX = x, centreZ = z, uvsPerFace = true, includeColours = true)
            mesh
        } + createDefaultCube(width = 100f, height = 0.1f, depth = 100f, centreY = -0.5f).get()

        val (albedoTexture) = glCreateTextures(GL_TEXTURE_2D, label = "Albedo texture")
        glTextureParameter(albedoTexture, GL_TEXTURE_MIN_FILTER, GL_LINEAR)
        glTextureParameter(albedoTexture, GL_TEXTURE_MAG_FILTER, GL_LINEAR)
        glTextureStorage2D(albedoTexture, internalFormat = GL_RGBA8, width = WINDOW_WIDTH, height = WINDOW_HEIGHT)

        val (positionTexture) = glCreateTextures(GL_TEXTURE_2D, label = "Position texture")
        glTextureParameter(positionTexture, GL_TEXTURE_MIN_FILTER, GL_LINEAR)
        glTextureParameter(positionTexture, GL_TEXTURE_MAG_FILTER, GL_LINEAR)
        glTextureParameter(positionTexture, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE)
        glTextureParameter(positionTexture, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE)
        glTextureStorage2D(positionTexture, internalFormat = GL_RGB16F, width = WINDOW_WIDTH, height = WINDOW_HEIGHT)

        val (normalTexture) = glCreateTextures(GL_TEXTURE_2D, label = "Normal texture")
        glTextureParameter(normalTexture, GL_TEXTURE_MIN_FILTER, GL_LINEAR)
        glTextureParameter(normalTexture, GL_TEXTURE_MAG_FILTER, GL_LINEAR)
        glTextureParameter(positionTexture, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE)
        glTextureParameter(positionTexture, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE)
        glTextureStorage2D(normalTexture, internalFormat = GL_RGB32F, width = WINDOW_WIDTH, height = WINDOW_HEIGHT)

        val (depthTexture) = glCreateTextures(GL_TEXTURE_2D, label = "Depth texture")
        glTextureParameter(depthTexture, GL_TEXTURE_MIN_FILTER, GL_LINEAR)
        glTextureParameter(depthTexture, GL_TEXTURE_MAG_FILTER, GL_LINEAR)
        glTextureStorage2D(depthTexture, internalFormat = GL_DEPTH_COMPONENT32F, width = WINDOW_WIDTH, height = WINDOW_HEIGHT)

        glBindFramebuffer(GL_FRAMEBUFFER, framebuffer) {
            glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, albedoTexture)
            glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT1, GL_TEXTURE_2D, positionTexture)
            glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT2, GL_TEXTURE_2D, normalTexture)
            glFramebufferTexture2D(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT, GL_TEXTURE_2D, depthTexture)

            if (glCheckFramebufferStatus(GL_FRAMEBUFFER) != GL_FRAMEBUFFER_COMPLETE)
                error("Incomplete framebuffer")
        }
        glNamedFramebufferDrawBuffers(framebuffer, GL_COLOR_ATTACHMENT0, GL_COLOR_ATTACHMENT1, GL_COLOR_ATTACHMENT2)

        glUseProgram(modelShaderProgram) {
            glUniformMatrix4fv(projectionLocation, projectionMatrix)
            glUniformMatrix4fv(viewLocation, viewMatrix)
            glUniformMatrix4fv(modelLocation, createTranslationMatrixValues(dz = -5f))
        }

        ssao.setProjectionTransform(projectionMatrix)
        ssao.setViewTransform(viewMatrix)

        SSAOExampleData(
            modelShaderProgram = modelShaderProgram,
            framebuffer = framebuffer,
            ssao = ssao,
            quad = quad,
            objects = objects,
            albedoTexture = albedoTexture,
            positionTexture = positionTexture,
            normalTexture = normalTexture,
        )
    }

    context (GLContext)
    override fun renderFrame(data: SSAOExampleData, time: Float) {
        glPushDebugGroupKHR(message = "Geometry pass")
        glViewport(w = WINDOW_WIDTH, h = WINDOW_HEIGHT)

        glBindFramebuffer(GL_FRAMEBUFFER, data.framebuffer) {
            glClearColor(0f, 0f, 0f)
            GL46C.glClearDepthf(1f)
            glEnable(GL_DEPTH_TEST)
            glClear(GL_COLOR_BUFFER_BIT + GL_DEPTH_BUFFER_BIT)

            glUseProgram(data.modelShaderProgram) {
                for (mesh in data.objects.map { it.get() }) {
                    mesh.draw()
                }
            }
        }

        glPopDebugGroupKHR()

        glPushDebugGroupKHR(message = "Screen draw pass")
        glClearColor(0.1f, 0.12f, 0.13f)
        glDisable(GL_DEPTH_TEST)
        glClear(GL_COLOR_BUFFER_BIT)

        glPushDebugGroupKHR(message = "SSAO pass") {
            data.ssao.bind {
                val bindings = listOf(GL_TEXTURE0 to data.positionTexture, GL_TEXTURE1 to data.normalTexture)

                for ((unit, texture) in bindings) {
                    glActiveTexture(unit)
                    glBindTexture(GL_TEXTURE_2D, texture)
                }

                data.quad.draw()

                for ((unit, _) in bindings) {
                    glActiveTexture(unit)
                    glBindTexture(GL_TEXTURE_2D)
                }
            }
        }
        glPopDebugGroupKHR()
    }
}

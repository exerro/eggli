package `05_fxaa`

import BaseExample
import WINDOW_HEIGHT
import WINDOW_WIDTH
import me.exerro.eggli.GL
import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.*
import me.exerro.eggli.gl.*
import me.exerro.egglix.math.createIdentityMatrixValues
import me.exerro.egglix.math.createPerspectiveProjectionMatrixValues
import me.exerro.egglix.math.createTranslationMatrixValues
import me.exerro.egglix.mesh.SimpleMesh
import me.exerro.egglix.mesh.createDefaultCube
import me.exerro.egglix.postprocessing.FXAA
import me.exerro.egglix.postprocessing.FullscreenQuad
import me.exerro.egglix.shader.ShaderAttribute
import me.exerro.egglix.shader.createModelVertexShaderSource
import me.exerro.egglix.shader.createShaderProgram
import me.exerro.lifetimes.Lifetime
import org.lwjgl.opengl.GL46C

/**
 * TODO
 */
class FXAAExample: BaseExample<FXAAExampleData>() {
    context (Lifetime)
    override fun createData(): GL<FXAAExampleData> = GL {
        val projectionMatrix = createPerspectiveProjectionMatrixValues(
            fov = 1.5f,
            aspectRatio = WINDOW_WIDTH.toFloat() / WINDOW_HEIGHT,
            near = 0.01f,
            far = 1000f,
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
        val fxaa = FXAA.create(WINDOW_WIDTH, WINDOW_HEIGHT)

        val objects = (1 .. 200).map {
            val x = Math.random().toFloat() * 50 - 25
            val y = Math.random().toFloat() * 20 - 10
            val z = -Math.random().toFloat() * 10
            val w = Math.random().toFloat() + 1
            val h = Math.random().toFloat() + 1
            val d = Math.random().toFloat() + 1
            val (mesh) = createDefaultCube(width = w, height = h, depth = d, centreX = x, centreY = y, centreZ = z, uvsPerFace = true, includeColours = true)
            mesh
        }

        val (albedoTexture) = glCreateTextures(GL_TEXTURE_2D, "Albedo Texture")
        glTextureParameter(albedoTexture, GL_TEXTURE_MIN_FILTER, GL_LINEAR)
        glTextureParameter(albedoTexture, GL_TEXTURE_MAG_FILTER, GL_LINEAR)
        glTextureStorage2D(albedoTexture, internalFormat = GL_RGBA8, width = WINDOW_WIDTH, height = WINDOW_HEIGHT)

        val (depthTexture) = glCreateTextures(GL_TEXTURE_2D)
        glTextureParameter(depthTexture, GL_TEXTURE_MIN_FILTER, GL_LINEAR)
        glTextureParameter(depthTexture, GL_TEXTURE_MAG_FILTER, GL_LINEAR)
        glTextureStorage2D(depthTexture, internalFormat = GL_DEPTH_COMPONENT32F, width = WINDOW_WIDTH, height = WINDOW_HEIGHT)

        glBindFramebuffer(GL_FRAMEBUFFER, framebuffer) {
            glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, albedoTexture)
            glFramebufferTexture2D(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT, GL_TEXTURE_2D, depthTexture)

            if (glCheckFramebufferStatus(GL_FRAMEBUFFER) != GL_FRAMEBUFFER_COMPLETE)
                error("Incomplete framebuffer")
        }
        glNamedFramebufferDrawBuffers(framebuffer, GL_COLOR_ATTACHMENT0)

        glUseProgram(modelShaderProgram) {
            glUniformMatrix4fv(projectionLocation, projectionMatrix)
            glUniformMatrix4fv(viewLocation, createIdentityMatrixValues())
            glUniformMatrix4fv(modelLocation, createTranslationMatrixValues(dz = -5f))
        }

        FXAAExampleData(
            modelShaderProgram = modelShaderProgram,
            framebuffer = framebuffer,
            fxaa = fxaa,
            quad = quad,
            objects = objects,
            albedoTexture = albedoTexture,
        )
    }

    context (GLContext)
    override fun renderFrame(data: FXAAExampleData, time: Float) {
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

        glUseProgram(data.fxaa.shader) {
            glPushDebugGroupKHR(message = "FXAA pass") {
                glBindTexture(GL_TEXTURE_2D, data.albedoTexture) {
                    data.quad.draw()
                }
            }
        }
        glPopDebugGroupKHR()
    }
}

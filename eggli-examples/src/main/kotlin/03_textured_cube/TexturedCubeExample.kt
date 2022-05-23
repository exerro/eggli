package `03_textured_cube`

import BaseExample
import WINDOW_HEIGHT
import WINDOW_WIDTH
import me.exerro.eggli.GL
import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.*
import me.exerro.eggli.gl.*
import me.exerro.eggli.util.*
import me.exerro.lifetimes.Lifetime
import runExample

/**
 * This example draws a coloured quad in the centre of the screen, fading in
 * and out. It uses some common code from [runExample] to avoid irrelevant
 * boilerplate which is demonstrated in the 01_hello_window example.
 *
 * The example is split into two parts: [createData] creates and sets up the
 * objects/state we'll use when rendering, and [renderFrame] renders each frame
 * continuously.
 */
class TexturedCubeExample: BaseExample<TexturedCubeExampleData>() {
    context (Lifetime)
    override fun createData(): GL<TexturedCubeExampleData> = GL {
        /**
         * Create a debug texture. This function creates a checkerboard pattern
         * with the two colours provided.
         */
        val (texture) = createDebugTexture(divisions = 3, c0 = 0xfafafa, c1 = 0x444444)
        /**
         * Create a perspective projection matrix for our window aspect ratio.
         * This returns a [FloatArray] that we can pass into OpenGL.
         */
        val projectionMatrix = createPerspectiveProjectionMatrixValues(
            fov = 1.5f, aspectRatio = WINDOW_WIDTH.toFloat() / WINDOW_HEIGHT)
        val (shaderProgram) = createShaderProgram(VERTEX_SHADER_SOURCE, FRAGMENT_SHADER_SOURCE)
        val (mesh) = createDefaultCube(includeColours = true, width = 3f, height = 3f, depth = 3f, centreZ = 0f, centreY = -2f, uvsPerFace = true)
        val timeLocation = glGetUniformLocation(shaderProgram, "u_time")
        val projectionLocation = glGetUniformLocation(shaderProgram, "u_projectionMatrix")

        glClearColor(0.1f, 0.12f, 0.13f)
        glEnable(GL_DEPTH_TEST) // enable depth testing to keep things looking sane
        glUseProgram(shaderProgram) { glUniformMatrix4fv(projectionLocation, projectionMatrix) }

        TexturedCubeExampleData(
            shaderProgram = shaderProgram,
            mesh = mesh,
            texture = texture,
            timeUniformLocation = timeLocation,
        )
    }

    context (GLContext)
    override fun renderFrame(data: TexturedCubeExampleData, time: Float) {
        val mesh = data.mesh.get()

        glClear(GL_COLOR_BUFFER_BIT + GL_DEPTH_BUFFER_BIT)

        glUseProgram(data.shaderProgram) {
            glUniform1f(data.timeUniformLocation, time)

            glBindTexture(GL_TEXTURE_2D, data.texture) {
                glBindVertexArray(mesh.vertexArray) {
                    if (mesh.usesElementBuffer)
                        glDrawElements(count = mesh.vertices)
                    else
                        glDrawArrays(count = mesh.vertices)
                }
            }
        }
    }
}

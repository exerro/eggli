package `02_hello_face`

import BaseExample
import me.exerro.eggli.GL
import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.*
import me.exerro.eggli.gl.*
import me.exerro.eggli.util.createDefaultFace
import me.exerro.eggli.util.createShaderProgram
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
class HelloFaceExample: BaseExample<HelloFaceExampleData>() {
    context (Lifetime)
    override fun createData(): GL<HelloFaceExampleData> = GL {
        /**
         * Create our shader program using the utility [createShaderProgram]
         * method. This wraps all the shader related operations including:
         * * creating shaders and a shader program
         * * setting shader sources
         * * compiling shaders
         * * attaching shaders to the shader program
         * * linking the shader program
         * * destroying the shaders
         */
        val (shaderProgram) = createShaderProgram(
            GL_VERTEX_SHADER to VERTEX_SHADER_SOURCE,
            GL_FRAGMENT_SHADER to FRAGMENT_SHADER_SOURCE,
        )

        /**
         * Create our face using the utility [createDefaultFace] method. This
         * wraps all the mesh related operations including:
         * * creating buffers and a vertex array
         * * setting buffer data
         * * setting vertex attributes
         * * creating an element array buffer, if required
         * Since we're only interested in colour, we disable UV and normal
         * generation and enable colours in the mesh.
         */
        val (mesh) = createDefaultFace(
            includeUVs = false,
            includeNormals = false,
            includeColours = true,
        )
        /**
         * Get the location of the time uniform, to set in each frame of our
         * render method.
         */
        val timeLocation = glGetUniformLocation(shaderProgram, "u_time")

        /** Set the background colour and enable blending. */
        glClearColor(0.1f, 0.12f, 0.13f)
        glEnable(GL_BLEND)
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA)

        /** Return the relevant objects. */
        HelloFaceExampleData(
            shaderProgram = shaderProgram,
            mesh = mesh,
            timeUniformLocation = timeLocation,
        )
    }

    context (GLContext)
    override fun renderFrame(data: HelloFaceExampleData, time: Float) {
        /**
         * Get our mesh data. [data.mesh] is a [GLResource], meaning we need to
         * call [get][GLResource.get] to access the actual mesh data. This is to
         * ensure that the mesh has not been destroyed.
         */
        val mesh = data.mesh.get()

        /** Clear the screen's color buffer. */
        glClear(GL_COLOR_BUFFER_BIT)

        /**
         * Use our shader program to draw. As an extension to the normal
         * [glUseProgram], this version accepts a block parameter to use the
         * shader within. Once the block has finished, the shader will be
         * unbound.
         */
        glUseProgram(data.shaderProgram) {
            /** Set the time uniform to the current time. */
            glUniform1f(data.timeUniformLocation, time)
            /**
             * Use our vertex array to draw. As an extension to the normal
             * [glBindVertexArray], this version accepts a block parameter to
             * use the vertex array within. Once the block has finished, the
             * vertex array will be unbound.
             */
            glBindVertexArray(mesh.vertexArray) {
                /**
                 * Depending on whether our mesh is using an element buffer,
                 * call the appropriate draw method. Both glDrawX functions have
                 * defaults for most parameters, allowing us to just pass in the
                 * number of vertices.
                 */
                if (mesh.usesElementBuffer)
                    glDrawElements(count = mesh.vertices)
                else
                    glDrawArrays(count = mesh.vertices)
            }
        }
    }
}

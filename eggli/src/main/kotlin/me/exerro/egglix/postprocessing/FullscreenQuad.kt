package me.exerro.egglix.postprocessing

import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.GL_ARRAY_BUFFER
import me.exerro.eggli.enum.GL_FLOAT
import me.exerro.eggli.gl.*
import me.exerro.eggli.types.GLAttributeIndex
import me.exerro.eggli.types.GLBuffer
import me.exerro.eggli.types.GLVertexArray
import me.exerro.lifetimes.Lifetime

/**
 * A [FullscreenQuad] is an object used to draw graphics over the whole screen.
 * The [buffer] bound to its [vao] is pre-generated with data that will draw
 * a fullscreen quad composed of two triangles with appropriate UVs. If
 * [flipped] is true, the top left of the screen would effectively have a UV
 * of `(0, 1)`, and the bottom left would have `(0, 0)`. Otherwise, the top left
 * would have `(0, 0)`, and the bottom right would have `(0, 1)`.
 * [vertices] contains the number of vertices used to draw the quad, which
 * should be 6. You can draw the quad using [FullscreenQuad.draw].
 */
class FullscreenQuad private constructor(
    val vao: GLVertexArray,
    val buffer: GLBuffer,
    val vertices: Int,
    val flipped: Boolean,
) {
    /**
     * Draw the quad by binding the VAO and calling `glDrawArrays` with the
     * right vertex count.
     */
    context (GLContext)
    fun draw() {
        glBindVertexArray(vao) {
            glDrawArrays(count = vertices)
        }
    }

    /** @see FullscreenQuad */
    companion object {
        /**
         * Default vertex attribute for the position component of vertices in
         * the buffer. Should match the binding for position in vertex shaders.
         */
        const val POSITION_ATTRIBUTE: GLAttributeIndex = 0

        /**
         * Default vertex attribute for the UV component of vertices in the
         * buffer. Should match the binding for UV in vertex shaders.
         */
        const val UV_ATTRIBUTE: GLAttributeIndex = 2

        /** Create a [FullscreenQuad]. */
        context (GLContext, Lifetime)
        fun create(
            flipped: Boolean = true,
            positionAttribute: GLAttributeIndex = POSITION_ATTRIBUTE,
            uvAttribute: GLAttributeIndex = UV_ATTRIBUTE,
        ): FullscreenQuad {
            val (vao) = glGenVertexArrays()
            val (buffer) = glCreateBuffers()
            val v0 = if (flipped) 1f else 0f
            val v1 = if (flipped) 0f else 1f

            glNamedBufferData(buffer, floatArrayOf(
                -1f,  1f, 0f, v0,
                -1f, -1f, 0f, v1,
                 1f, -1f, 1f, v1,
                -1f,  1f, 0f, v0,
                 1f, -1f, 1f, v1,
                 1f,  1f, 1f, v0,
            ))

            glBindVertexArray(vao) {
                glBindBuffer(GL_ARRAY_BUFFER, buffer) {
                    glVertexAttribPointer(positionAttribute, 2, GL_FLOAT, false, 4 * 4, 0 * 4L)
                    glVertexAttribPointer(uvAttribute, 2, GL_FLOAT, false, 4 * 4, 2 * 4L)
                }
            }

            glEnableVertexAttribArray(vao, positionAttribute)
            glEnableVertexAttribArray(vao, uvAttribute)

            return FullscreenQuad(
                vao = vao,
                buffer = buffer,
                vertices = 6,
                flipped = flipped,
            )
        }
    }
}

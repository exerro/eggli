package me.exerro.egglix.postprocessing

import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.GL_ARRAY_BUFFER
import me.exerro.eggli.enum.GL_FLOAT
import me.exerro.eggli.gl.*
import me.exerro.eggli.types.GLBuffer
import me.exerro.eggli.types.GLVertexArray
import me.exerro.lifetimes.Lifetime

/** TODO */
class FullscreenQuad private constructor(
    val vao: GLVertexArray,
    val buffer: GLBuffer,
    val vertices: Int,
) {
    /** TODO */
    context (GLContext)
    fun draw() {
        glBindVertexArray(vao) {
            glDrawArrays(count = vertices)
        }
    }

    companion object {
        /** TODO */
        context (GLContext, Lifetime)
        fun create(flipped: Boolean = true): FullscreenQuad {
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
                    glVertexAttribPointer(0, 2, GL_FLOAT, false, 4 * 4, 0 * 4L)
                    glVertexAttribPointer(2, 2, GL_FLOAT, false, 4 * 4, 2 * 4L)
                }
            }

            glEnableVertexAttribArray(vao, 0)
            glEnableVertexAttribArray(vao, 2)

            return FullscreenQuad(
                vao = vao,
                buffer = buffer,
                vertices = 6,
            )
        }
    }
}

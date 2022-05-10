package me.exerro.eggli.enum

import org.lwjgl.opengl.GL46C

/** TODO */
class GLClearBit(val glValue: Int, val glName: String) {
    /** TODO */
    operator fun plus(other: GLClearBit) =
        fromGLValue(glValue or other.glValue)

    override fun toString() = glName

    /** @see GLClearBit */
    companion object {
        /** TODO */
        fun fromGLValue(glValue: Int): GLClearBit {
            val names = listOfNotNull(
                "GL_COLOR_BUFFER_BIT".takeIf { glValue and GL46C.GL_COLOR_BUFFER_BIT != 0 },
                "GL_DEPTH_BUFFER_BIT".takeIf { glValue and GL46C.GL_DEPTH_BUFFER_BIT != 0 },
                "GL_STENCIL_BUFFER_BIT".takeIf { glValue and GL46C.GL_STENCIL_BUFFER_BIT != 0 },
            )
            return GLClearBit(glValue, names.joinToString(" + "))
        }

        /** TODO */
        val ColorBuffer = GLClearBit(GL46C.GL_COLOR_BUFFER_BIT, glName = "GL_COLOR_BUFFER_BIT")

        /** TODO */
        val DepthBuffer = GLClearBit(GL46C.GL_DEPTH_BUFFER_BIT, glName = "GL_DEPTH_BUFFER_BIT")

        /** TODO */
        val StencilBuffer = GLClearBit(GL46C.GL_STENCIL_BUFFER_BIT, glName = "GL_STENCIL_BUFFER_BIT")
    }
}

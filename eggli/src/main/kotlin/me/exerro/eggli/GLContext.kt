package me.exerro.eggli

import org.lwjgl.opengl.GL46C

/** TODO */
class GLContext internal constructor(
    /** TODO */
    val worker: GLWorker,
) {
    /** TODO */
    fun <T> GL<T>.get() = evaluate()

    /** TODO */
    operator fun <T> GL<T>.component1() = evaluate()

    /** TODO */
    fun glCheckForErrors() = when (GL46C.glGetError()) {
        GL46C.GL_NO_ERROR -> Unit
        GL46C.GL_INVALID_ENUM -> error("GL_INVALID_ENUM")
        GL46C.GL_INVALID_VALUE -> error("GL_INVALID_VALUE")
        GL46C.GL_INVALID_OPERATION -> error("GL_INVALID_OPERATION")
        GL46C.GL_INVALID_FRAMEBUFFER_OPERATION -> error("GL_INVALID_FRAMEBUFFER_OPERATION")
        GL46C.GL_OUT_OF_MEMORY -> error("GL_OUT_OF_MEMORY")
        GL46C.GL_STACK_UNDERFLOW -> error("GL_STACK_UNDERFLOW")
        GL46C.GL_STACK_OVERFLOW -> error("GL_STACK_OVERFLOW")
        else -> error("Unknown GL error")
    }

    /** @see GLContext */
    companion object {
        /**
         * Getter for the [GLContext] in scope, because Kotlin can be a pain
         * with contexts sometimes.
         */
        context (GLContext)
        fun get() = this@GLContext
    }
}

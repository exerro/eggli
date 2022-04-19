package me.exerro.eggli.enum

import org.lwjgl.opengl.GL46C

/** TODO */
enum class GLBufferUsage(val glValue: Int, val glName: String) {
    /** TODO */
    StreamDraw(GL46C.GL_STREAM_DRAW, glName = "GL_STREAM_DRAW"),

    /** TODO */
    StreamRead(GL46C.GL_STREAM_READ, glName = "GL_STREAM_READ"),

    /** TODO */
    StreamCopy(GL46C.GL_STREAM_COPY, glName = "GL_STREAM_COPY"),

    /** TODO */
    StaticDraw(GL46C.GL_STATIC_DRAW, glName = "GL_STATIC_DRAW"),

    /** TODO */
    StaticRead(GL46C.GL_STATIC_READ, glName = "GL_STATIC_READ"),

    /** TODO */
    StaticCopy(GL46C.GL_STATIC_COPY, glName = "GL_STATIC_COPY"),

    /** TODO */
    DynamicDraw(GL46C.GL_DYNAMIC_DRAW, glName = "GL_DYNAMIC_DRAW"),

    /** TODO */
    DynamicRead(GL46C.GL_DYNAMIC_READ, glName = "GL_DYNAMIC_READ"),

    /** TODO */
    DynamicCopy(GL46C.GL_DYNAMIC_COPY, glName = "GL_DYNAMIC_COPY");

    override fun toString() = glName

    /** @see GLBufferUsage */
    companion object {
        /** TODO */
        fun fromGLValue(glValue: Int) = values()
            .firstOrNull { it.glValue == glValue }
    }
}

package me.exerro.eggli.enum

import org.lwjgl.opengl.GL46C

/** TODO */
enum class GLBufferTarget(val glValue: Int, val glName: String) {
    /** TODO */
    Array(GL46C.GL_ARRAY_BUFFER, glName = "GL_ARRAY_BUFFER"),

    /** TODO */
    AtomicCounter(GL46C.GL_ATOMIC_COUNTER_BUFFER, glName = "GL_ATOMIC_COUNTER_BUFFER"),

    /** TODO */
    CopyRead(GL46C.GL_COPY_READ_BUFFER, glName = "GL_COPY_READ_BUFFER"),

    /** TODO */
    CopyWrite(GL46C.GL_COPY_WRITE_BUFFER, glName = "GL_COPY_WRITE_BUFFER"),

    /** TODO */
    DrawIndirect(GL46C.GL_DRAW_INDIRECT_BUFFER, glName = "GL_DRAW_INDIRECT_BUFFER"),

    /** TODO */
    DispatchIndirect(GL46C.GL_DISPATCH_INDIRECT_BUFFER, glName = "GL_DISPATCH_INDIRECT_BUFFER"),

    /** TODO */
    ElementArray(GL46C.GL_ELEMENT_ARRAY_BUFFER, glName = "GL_ELEMENT_ARRAY_BUFFER"),

    /** TODO */
    PixelPack(GL46C.GL_PIXEL_PACK_BUFFER, glName = "GL_PIXEL_PACK_BUFFER"),

    /** TODO */
    PixelUnpack(GL46C.GL_PIXEL_UNPACK_BUFFER, glName = "GL_PIXEL_UNPACK_BUFFER"),

    /** TODO */
    Query(GL46C.GL_QUERY_BUFFER, glName = "GL_QUERY_BUFFER"),

    /** TODO */
    ShaderStorage(GL46C.GL_SHADER_STORAGE_BUFFER, glName = "GL_SHADER_STORAGE_BUFFER"),

    /** TODO */
    Texture(GL46C.GL_TEXTURE_BUFFER, glName = "GL_TEXTURE_BUFFER"),

    /** TODO */
    TransformFeedback(GL46C.GL_TRANSFORM_FEEDBACK_BUFFER, glName = "GL_TRANSFORM_FEEDBACK_BUFFER"),

    /** TODO */
    Uniform(GL46C.GL_UNIFORM_BUFFER, glName = "GL_UNIFORM_BUFFER");

    override fun toString() = glName

    /** @see GLBufferTarget */
    companion object {
        /** TODO */
        fun fromGLValue(glValue: Int) = values()
            .firstOrNull { it.glValue == glValue }
    }
}

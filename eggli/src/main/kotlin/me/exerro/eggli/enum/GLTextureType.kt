package me.exerro.eggli.enum

import org.lwjgl.opengl.GL46C

/** TODO */
enum class GLTextureType(val glValue: Int, val glName: String) {
    /** TODO */
    Texture1D(GL46C.GL_TEXTURE_1D, glName = "GL_TEXTURE_1D"),

    /** TODO */
    Texture1DArray(GL46C.GL_TEXTURE_1D_ARRAY, glName = "GL_TEXTURE_1D_ARRAY"),

    /** TODO */
    Texture2D(GL46C.GL_TEXTURE_2D, glName = "GL_TEXTURE_2D"),

    /** TODO */
    Texture2DArray(GL46C.GL_TEXTURE_2D_ARRAY, glName = "GL_TEXTURE_2D_ARRAY"),

    /** TODO */
    Texture2DMultisample(GL46C.GL_TEXTURE_2D_MULTISAMPLE, glName = "GL_TEXTURE_2D_MULTISAMPLE"),

    /** TODO */
    Texture2DMultisampleArray(GL46C.GL_TEXTURE_2D_MULTISAMPLE_ARRAY, glName = "GL_TEXTURE_2D_MULTISAMPLE_ARRAY"),

    /** TODO */
    Texture3D(GL46C.GL_TEXTURE_3D, glName = "GL_TEXTURE_3D"),

    /** TODO */
    CubeMap(GL46C.GL_TEXTURE_CUBE_MAP, glName = "GL_TEXTURE_CUBE_MAP"),

    /** TODO */
    CubeMapArray(GL46C.GL_TEXTURE_CUBE_MAP_ARRAY, glName = "GL_TEXTURE_CUBE_MAP_ARRAY"),

    /** TODO */
    Rectangle(GL46C.GL_TEXTURE_RECTANGLE, glName = "GL_TEXTURE_RECTANGLE");

    override fun toString() = glName

    /** @see GLTextureType */
    companion object {
        /** TODO */
        fun fromGLValue(glValue: Int) = values()
            .firstOrNull { it.glValue == glValue }
    }
}

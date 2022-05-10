package me.exerro.eggli.enum

import org.lwjgl.opengl.GL46C

/** TODO */
@Suppress("SpellCheckingInspection")
sealed class GLenum(
    val glValue: Int,
    val glName: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other is GLenum && glValue == other.glValue) return true
        return glValue == other
    }

    override fun hashCode() = glValue
    override fun toString() = glName
}

object GL_ALPHA: GLenum(GL46C.GL_ALPHA, glName = "GL_ALPHA"), GLTextureParameter.Swizzle, GLTextureSwizzleValue
object GL_BLUE: GLenum(GL46C.GL_BLUE, glName = "GL_BLUE"), GLTextureParameter.Swizzle, GLTextureSwizzleValue
object GL_GREEN: GLenum(GL46C.GL_GREEN, glName = "GL_GREEN"), GLTextureParameter.Swizzle, GLTextureSwizzleValue
object GL_ONE: GLenum(GL46C.GL_ONE, glName = "GL_ONE"), GLTextureSwizzleValue
object GL_RED: GLenum(GL46C.GL_RED, glName = "GL_RED"), GLTextureParameter.Swizzle, GLTextureSwizzleValue
object GL_TEXTURE_MAG_FILTER: GLenum(GL46C.GL_TEXTURE_MAG_FILTER, glName = "GL_TEXTURE_MAG_FILTER")
object GL_TEXTURE_MIN_FILTER: GLenum(GL46C.GL_TEXTURE_MIN_FILTER, glName = "GL_TEXTURE_MIN_FILTER")
object GL_ZERO: GLenum(GL46C.GL_ZERO, glName = "GL_ZERO"), GLTextureSwizzleValue

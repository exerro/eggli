package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.GLTextureImageFormat
import me.exerro.eggli.enum.GLTextureImageInternalFormat
import me.exerro.eggli.enum.GLTextureImageType
import me.exerro.eggli.enum.GLTextureTarget
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext)
@Deprecated(
    message = "In many cases, glTextureStorage2D + glTextureSubImage2D is preferred",
    level = DeprecationLevel.WARNING,
)
fun glTexImage2D(
    target: GLTextureTarget,
    level: Int = 0,
    internalFormat: GLTextureImageInternalFormat,
    width: Int,
    height: Int,
    border: Int,
    format: GLTextureImageFormat,
    type: GLTextureImageType,
    data: IntArray,
) {
    GL46C.glTexImage2D(target.glValue, level, internalFormat.glValue, width, height, border, format.glValue, type.glValue, data)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
@Deprecated(
    message = "In many cases, glTextureStorage2D + glTextureSubImage2D is preferred",
    level = DeprecationLevel.WARNING,
)
fun glTexImage2D(
    target: GLTextureTarget,
    level: Int = 0,
    internalFormat: GLTextureImageInternalFormat,
    width: Int,
    height: Int,
    border: Int,
    format: GLTextureImageFormat,
    type: GLTextureImageType,
    data: FloatArray,
) {
    GL46C.glTexImage2D(target.glValue, level, internalFormat.glValue, width, height, border, format.glValue, type.glValue, data)
    glCheckForErrors()
}

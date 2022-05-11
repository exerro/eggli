package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.Data
import me.exerro.eggli.GLDebugger.LogEntity.Texture
import me.exerro.eggli.enum.*
import me.exerro.eggli.types.GLTexture
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext, GLDebugger.Context)
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
    glLog(Data, Texture, "glTexImage2D($target, $level, $internalFormat, $width, $height, $border, $format, $type, ...) [int]")
    GL46C.glTexImage2D(target.glValue, level, internalFormat.glValue, width, height, border, format.glValue, type.glValue, data)
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
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
    glLog(Data, Texture, "glTexImage2D($target, $level, $internalFormat, $width, $height, $border, $format, $type, ...) [int]")
    GL46C.glTexImage2D(target.glValue, level, internalFormat.glValue, width, height, border, format.glValue, type.glValue, data)
    glCheckForErrors()
}

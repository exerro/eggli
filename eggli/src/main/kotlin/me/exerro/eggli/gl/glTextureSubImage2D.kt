package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.Data
import me.exerro.eggli.GLDebugger.LogEntity.Texture
import me.exerro.eggli.enum.*
import me.exerro.eggli.types.GLTexture
import org.lwjgl.opengl.GL46C
import java.nio.ByteBuffer

/** TODO */
context (GLContext, GLDebugger.Context)
fun glTextureSubImage2D(
    texture: GLTexture,
    level: Int = 0,
    xOffset: Int = 0,
    yOffset: Int = 0,
    width: Int,
    height: Int,
    format: GLTextureImageFormat,
    type: GLTextureImageType,
    pixels: ByteBuffer,
) {
    glLog(Data, Texture, "glTextureSubImage2D($texture, $level, $xOffset, $yOffset, $width, $height, $format, $type, ...) [byte]")
    GL46C.glTextureSubImage2D(texture.get(), level, xOffset, yOffset, width, height, format.glValue, type.glValue, pixels)
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun glTextureSubImage2D(
    texture: GLTexture,
    level: Int = 0,
    xOffset: Int = 0,
    yOffset: Int = 0,
    width: Int,
    height: Int,
    format: GLTextureImageFormat,
    type: GLTextureImageType,
    pixels: IntArray,
) {
    glLog(Data, Texture, "glTextureSubImage2D($texture, $level, $xOffset, $yOffset, $width, $height, $format, $type, ...) [int]")
    GL46C.glTextureSubImage2D(texture.get(), level, xOffset, yOffset, width, height, format.glValue, type.glValue, pixels)
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun glTextureSubImage2D(
    texture: GLTexture,
    level: Int = 0,
    xOffset: Int = 0,
    yOffset: Int = 0,
    width: Int,
    height: Int,
    format: GLTextureImageFormat,
    type: GLTextureImageType,
    pixels: ShortArray,
) {
    glLog(Data, Texture, "glTextureSubImage2D($texture, $level, $xOffset, $yOffset, $width, $height, $format, $type, ...) [short]")
    GL46C.glTextureSubImage2D(texture.get(), level, xOffset, yOffset, width, height, format.glValue, type.glValue, pixels)
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun glTextureSubImage2D(
    texture: GLTexture,
    level: Int = 0,
    xOffset: Int = 0,
    yOffset: Int = 0,
    width: Int,
    height: Int,
    format: GLTextureImageFormat,
    type: GLTextureImageType,
    pixels: FloatArray,
) {
    glLog(Data, Texture, "glTextureSubImage2D($texture, $level, $xOffset, $yOffset, $width, $height, $format, $type, ...) [float]")
    GL46C.glTextureSubImage2D(texture.get(), level, xOffset, yOffset, width, height, format.glValue, type.glValue, pixels)
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun glTextureSubImage2D(
    texture: GLTexture,
    level: Int = 0,
    xOffset: Int = 0,
    yOffset: Int = 0,
    width: Int,
    height: Int,
    format: GLTextureImageFormat,
    type: GLTextureImageType,
    pixels: DoubleArray,
) {
    glLog(Data, Texture, "glTextureSubImage2D($texture, $level, $xOffset, $yOffset, $width, $height, $format, $type, ...) [double]")
    GL46C.glTextureSubImage2D(texture.get(), level, xOffset, yOffset, width, height, format.glValue, type.glValue, pixels)
    glCheckForErrors()
}

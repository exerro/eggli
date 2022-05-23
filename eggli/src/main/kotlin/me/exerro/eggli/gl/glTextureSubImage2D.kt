package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.GLTextureImageFormat
import me.exerro.eggli.enum.GLTextureImageType
import me.exerro.eggli.types.GLTexture
import org.lwjgl.opengl.GL46C
import java.nio.ByteBuffer

/** TODO */
context (GLContext)
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
    GL46C.glTextureSubImage2D(texture.get(), level, xOffset, yOffset, width, height, format.glValue, type.glValue, pixels)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
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
    GL46C.glTextureSubImage2D(texture.get(), level, xOffset, yOffset, width, height, format.glValue, type.glValue, pixels)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
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
    GL46C.glTextureSubImage2D(texture.get(), level, xOffset, yOffset, width, height, format.glValue, type.glValue, pixels)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
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
    GL46C.glTextureSubImage2D(texture.get(), level, xOffset, yOffset, width, height, format.glValue, type.glValue, pixels)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
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
    GL46C.glTextureSubImage2D(texture.get(), level, xOffset, yOffset, width, height, format.glValue, type.glValue, pixels)
    glCheckForErrors()
}

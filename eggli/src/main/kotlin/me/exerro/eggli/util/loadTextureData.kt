package me.exerro.eggli.util

import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.GL_RGBA
import me.exerro.eggli.enum.GL_UNSIGNED_BYTE
import me.exerro.eggli.gl.glTextureStorage2D
import me.exerro.eggli.gl.glTextureSubImage2D
import me.exerro.eggli.types.GLTexture
import org.lwjgl.BufferUtils
import org.lwjgl.BufferUtils.createIntBuffer
import org.lwjgl.stb.STBImage
import java.io.InputStream

/**
 * Load RGB image data into a texture from a byte array. The [bytes] provided
 * should be encoded into a format accepted by STB (e.g. PNG, see [STBImage].)
 *
 * Note: The byte array can be obtained from [ClassLoader.getResourceAsStream] .
 * [readBytes][InputStream.readBytes]
 */
context (GLContext)
fun loadTextureData(
    texture: GLTexture,
    bytes: ByteArray,
) {
    val buffer = BufferUtils.createByteBuffer(bytes.size)
    buffer.put(bytes)
    buffer.flip()

    val w = createIntBuffer(1)
    val h = createIntBuffer(1)
    val channels = createIntBuffer(1)
    val data = STBImage.stbi_load_from_memory(buffer, w, h, channels, STBImage.STBI_rgb_alpha)
        ?: throw RuntimeException(STBImage.stbi_failure_reason())
    val width = w.get()
    val height = h.get()

    glTextureStorage2D(texture, width = width, height = height)
    glTextureSubImage2D(texture, width = width, height = height, format = GL_RGBA, type = GL_UNSIGNED_BYTE, pixels = data)
    STBImage.stbi_image_free(data)
    glCheckForErrors()
}

package me.exerro.egglix.texture

import me.exerro.eggli.GL
import me.exerro.eggli.enum.*
import me.exerro.eggli.gl.*
import me.exerro.lifetimes.Lifetime

/**
 * Create a [width]x[height] texture with [divisions]^2 blocks of colour. The
 * default colours used are magenta ([c0]) and black ([c1]), but these can be
 * changed to any RGB value encoded as a 24 bit integer.
 */
context (Lifetime)
fun createDebugTexture(
    width: Int = 64,
    height: Int = 64,
    divisions: Int = 2,
    c0: Int = 0xff00ff,
    c1: Int = 0x000000,
) = GL {
    val (texture) = glCreateTextures(GL_TEXTURE_2D)
    val data = IntArray(width * height) { index ->
        val y = index / width
        val x = index % width
        val xd = x * divisions / width
        val yd = y * divisions / height

        if (xd % 2 == yd % 2) c0
        else c1
    }

    glTextureStorage2D(texture, width = width, height = height)
    glTextureSubImage2D(texture, width = width, height = height, format = GL_RGBA, type = GL_UNSIGNED_BYTE, pixels = data)
    glTextureParameter(texture, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE)
    glTextureParameter(texture, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE)
    glTextureParameter(texture, GL_TEXTURE_MIN_FILTER, GL_NEAREST)
    glTextureParameter(texture, GL_TEXTURE_MAG_FILTER, GL_NEAREST)
    glBindTexture(GL_TEXTURE_2D, texture) { glGenerateMipmap(GL_TEXTURE_2D) }

    texture
}

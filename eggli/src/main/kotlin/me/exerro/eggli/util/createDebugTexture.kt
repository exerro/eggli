package me.exerro.eggli.util

import me.exerro.eggli.GL
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.enum.*
import me.exerro.eggli.gl.*
import me.exerro.lifetimes.Lifetime

/** TODO */
context (Lifetime, GLDebugger.Context)
fun createDebugTexture(
    width: Int = 8,
    height: Int = 8,
    c0: Int = 0xff00ff,
    c1: Int = 0x000000,
) = GL {
    val (texture) = glGenTextures()
    val data = IntArray(width * height) { index ->
        val y = index / width
        val x = index % width

        if (x < width / 2 == y < height / 2) c0
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

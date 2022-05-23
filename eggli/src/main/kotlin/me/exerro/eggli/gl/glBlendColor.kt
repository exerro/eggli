package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import org.lwjgl.opengl.GL46C

/** @see <a href="https://docs.gl/gl4/glBlendColor">Reference Page</a> */
context (GLContext)
fun glBlendColor(
    red: Float,
    green: Float,
    blue: Float,
    alpha: Float = 1f,
) {
    GL46C.glBlendColor(red, green, blue, alpha)
    glCheckForErrors()
}

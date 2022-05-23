package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.GLBlendEquation
import org.lwjgl.opengl.GL46C

/** @see <a href="https://docs.gl/gl4/glBlendEquation">Reference Page</a> */
context (GLContext)
fun glBlendEquation(equation: GLBlendEquation) {
    GL46C.glBlendEquation(equation.glValue)
    glCheckForErrors()
}

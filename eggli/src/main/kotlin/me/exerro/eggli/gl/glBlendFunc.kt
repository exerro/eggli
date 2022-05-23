package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.GLBlendFactor
import org.lwjgl.opengl.GL46C

/** @see <a href="https://docs.gl/gl4/glBlendFunc">Reference Page</a> */
context (GLContext)
fun glBlendFunc(sfactor: GLBlendFactor, dfactor: GLBlendFactor) {
    GL46C.glBlendFunc(sfactor.glValue, dfactor.glValue)
    glCheckForErrors()
}

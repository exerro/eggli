package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import org.lwjgl.opengl.GL46C

/** @see <a href="https://docs.gl/gl4/glStencilMask">Reference Page</a> */
context (GLContext)
fun glStencilMask(mask: Int) {
    GL46C.glStencilMask(mask)
    glCheckForErrors()
}

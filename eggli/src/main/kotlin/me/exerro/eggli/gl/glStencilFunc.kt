package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.GLStencilFunction
import org.lwjgl.opengl.GL46C

/** @see <a href="https://docs.gl/gl4/glStencilFunc">Reference Page</a> */
context (GLContext)
fun glStencilFunc(func: GLStencilFunction, ref: Int, mask: Int = 0xff) {
    GL46C.glStencilFunc(func.glValue, ref, mask)
    glCheckForErrors()
}

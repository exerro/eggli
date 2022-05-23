package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import org.lwjgl.opengl.GL46C

/**
 * @see <a href="https://docs.gl/gl4/glClearStencil">Reference Page</a>
 */
context (GLContext)
fun glClearStencil(stencil: Int) {
    GL46C.glClearStencil(stencil)
    glCheckForErrors()
}

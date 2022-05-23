package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.StateChanged
import me.exerro.eggli.GLDebugger.LogEntity.State
import org.lwjgl.opengl.GL46C

/**
 * @see <a href="https://docs.gl/gl4/glClearStencil">Reference Page</a>
 */
context (GLContext, GLDebugger.Context)
fun glClearStencil(stencil: Int) {
    GL46C.glClearStencil(stencil)
    glCheckForErrors()
}

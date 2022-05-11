package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.StateChanged
import me.exerro.eggli.GLDebugger.LogEntity.State
import me.exerro.eggli.enum.GLStencilFunction
import org.lwjgl.opengl.GL46C

/** @see <a href="https://docs.gl/gl4/glDepthMask">Reference Page</a> */
context (GLContext, GLDebugger.Context)
fun glStencilFunc(func: GLStencilFunction, ref: Int, mask: Int = 0xff) {
    glLog(StateChanged, State, "Setting stencil func to $func (ref = $ref, mask = $mask)")
    GL46C.glStencilFunc(func.glValue, ref, mask)
    glCheckForErrors()
}

package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.StateChanged
import me.exerro.eggli.GLDebugger.LogEntity.State
import me.exerro.eggli.enum.GLBlendFactor
import org.lwjgl.opengl.GL46C

/** @see <a href="https://docs.gl/gl4/glBlendFunc">Reference Page</a> */
context (GLContext, GLDebugger.Context)
fun glBlendFunc(sfactor: GLBlendFactor, dfactor: GLBlendFactor) {
    glLog(StateChanged, State, "Setting blend func to (source = $sfactor, destination = $dfactor)")
    GL46C.glBlendFunc(sfactor.glValue, dfactor.glValue)
    glCheckForErrors()
}

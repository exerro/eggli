package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.StateChanged
import me.exerro.eggli.GLDebugger.LogEntity.State
import org.lwjgl.opengl.GL46C

/** @see <a href="https://docs.gl/gl4/glDepthMask">Reference Page</a> */
context (GLContext, GLDebugger.Context)
fun glDepthMask(flag: Boolean) {
    glLog(StateChanged, State, "Setting depth mask to $flag")
    GL46C.glDepthMask(flag)
    glCheckForErrors()
}
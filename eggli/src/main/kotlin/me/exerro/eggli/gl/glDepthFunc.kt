package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.StateChanged
import me.exerro.eggli.GLDebugger.LogEntity.State
import me.exerro.eggli.enum.GLDepthFunction
import org.lwjgl.opengl.GL46C

/** @see <a href="https://docs.gl/gl4/glDepthFunc">Reference Page</a> */
context (GLContext, GLDebugger.Context)
fun glDepthFunc(func: GLDepthFunction) {
    glLog(StateChanged, State, "Setting depth function to $func")
    GL46C.glDepthFunc(func.glValue)
    glCheckForErrors()
}

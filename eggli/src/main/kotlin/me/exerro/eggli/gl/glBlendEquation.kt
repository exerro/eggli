package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.StateChanged
import me.exerro.eggli.GLDebugger.LogEntity.State
import me.exerro.eggli.enum.GLBlendEquation
import org.lwjgl.opengl.GL46C

/** @see <a href="https://docs.gl/gl4/glBlendEquation">Reference Page</a> */
context (GLContext, GLDebugger.Context)
fun glBlendEquation(equation: GLBlendEquation) {
    glLog(StateChanged, State, "Setting blend equation to $equation")
    GL46C.glBlendEquation(equation.glValue)
    glCheckForErrors()
}

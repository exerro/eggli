package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.StateChanged
import me.exerro.eggli.GLDebugger.LogEntity.State
import org.lwjgl.opengl.GL46C

/** @see <a href="https://docs.gl/gl4/glBlendColor">Reference Page</a> */
context (GLContext, GLDebugger.Context)
fun glBlendColor(
    red: Float,
    green: Float,
    blue: Float,
    alpha: Float = 1f,
) {
    glLog(StateChanged, State, "glBlendColor($red, $green, $blue, $alpha)")
    GL46C.glBlendColor(red, green, blue, alpha)
    glCheckForErrors()
}

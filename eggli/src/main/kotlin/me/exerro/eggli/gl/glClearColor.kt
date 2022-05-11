package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.StateChanged
import me.exerro.eggli.GLDebugger.LogEntity.State
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext, GLDebugger.Context)
fun glClearColor(red: Float, green: Float, blue: Float, alpha: Float = 1f) {
    GL46C.glClearColor(red, green, blue, alpha)
    glLog(StateChanged, State, "glClearColor($red, $green, $blue, $alpha)")
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun glClearColor(rgb: Float, alpha: Float = 1f) {
    GL46C.glClearColor(rgb, rgb, rgb, alpha)
    glLog(StateChanged, State, "glClearColor($rgb, $rgb, $rgb, $alpha)")
    glCheckForErrors()
}
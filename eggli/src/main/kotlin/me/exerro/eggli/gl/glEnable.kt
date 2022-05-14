package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.*
import me.exerro.eggli.GLDebugger.LogEntity.State
import me.exerro.eggli.enum.GLOption
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext, GLDebugger.Context)
fun glEnable(target: GLOption) {
    glLog(StateChanged, State, "Enabling $target")
    GL46C.glEnable(target.glValue)
    glCheckForErrors()
}

package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.*
import me.exerro.eggli.GLDebugger.LogEntity.State
import me.exerro.eggli.enum.GLTarget
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext, GLDebugger.Context)
fun glEnable(target: GLTarget) {
    GL46C.glEnable(target.glValue)
    glLog(StateChanged, State, "Enabled $target")
    glCheckForErrors()
}

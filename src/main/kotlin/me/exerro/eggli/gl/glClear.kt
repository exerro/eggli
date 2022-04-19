package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.*
import me.exerro.eggli.GLDebugger.LogEntity.DrawTarget
import me.exerro.eggli.enum.GLClearBit
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext, GLDebugger.Context)
fun glClear(clearBits: GLClearBit) {
    GL46C.glClear(clearBits.glValue)
    glLog(DrawCall, DrawTarget, "glClear($clearBits)")
    glCheckForErrors()
}

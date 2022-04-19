package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.*
import me.exerro.eggli.GLDebugger.LogEntity.DrawTarget
import me.exerro.eggli.GLDebugger.LogEntity.State
import me.exerro.eggli.enum.GLDrawMode
import me.exerro.eggli.enum.GLTarget
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext, GLDebugger.Context)
fun glDrawArrays(
    mode: GLDrawMode,
    first: Int,
    count: Int,
) {
    GL46C.glDrawArrays(mode.glValue, first, count)
    glLog(DrawCall, DrawTarget, "glDrawArrays($mode, $first, $count)")
    glCheckForErrors()
}

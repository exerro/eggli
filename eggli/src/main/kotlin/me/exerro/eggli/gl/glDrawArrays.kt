package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.*
import me.exerro.eggli.GLDebugger.LogEntity.DrawTarget
import me.exerro.eggli.enum.GLDrawMode
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext, GLDebugger.Context)
fun glDrawArrays(
    mode: GLDrawMode = GLDrawMode.Triangles,
    first: Int = 0,
    count: Int,
) {
    GL46C.glDrawArrays(mode.glValue, first, count)
    glLog(DrawCall, DrawTarget, "glDrawArrays($mode, $first, $count)")
    glCheckForErrors()
}
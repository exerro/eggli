package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.DrawCall
import me.exerro.eggli.GLDebugger.LogEntity.DrawTarget
import me.exerro.eggli.enum.GLDrawMode
import me.exerro.eggli.enum.GL_TRIANGLES
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext, GLDebugger.Context)
fun glDrawArrays(
    mode: GLDrawMode = GL_TRIANGLES,
    first: Int = 0,
    count: Int,
) {
    glLog(DrawCall, DrawTarget, "glDrawArrays($mode, $first, $count)")
    GL46C.glDrawArrays(mode.glValue, first, count)
    glCheckForErrors()
}

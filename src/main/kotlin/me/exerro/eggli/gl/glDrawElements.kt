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
fun glDrawElements(
    mode: GLDrawMode,
    count: Int,
    type: Int = GL46C.GL_UNSIGNED_INT,
    indices: Long = 0L
) {
    GL46C.glDrawElements(mode.glValue, count, type, indices)
    glLog(DrawCall, DrawTarget, "glDrawElements($mode, $count, $type, $indices)")
    glCheckForErrors()
}

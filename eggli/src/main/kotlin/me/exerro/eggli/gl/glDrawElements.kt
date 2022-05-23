package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.*
import me.exerro.eggli.GLDebugger.LogEntity.DrawTarget
import me.exerro.eggli.enum.GLDrawMode
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext, GLDebugger.Context)
fun glDrawElements(
    mode: GLDrawMode = GLDrawMode.Triangles,
    count: Int,
    type: Int = GL46C.GL_UNSIGNED_INT, // TODO!
    indices: Long = 0L
) {
    glLog(DrawCall, DrawTarget, "glDrawElements($mode, $count, $type, $indices)")
    GL46C.glDrawElements(mode.glValue, count, type, indices)
    glCheckForErrors()
}

package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.StateChanged
import me.exerro.eggli.GLDebugger.LogEntity.VArray
import me.exerro.eggli.enum.GLType
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext, GLDebugger.Context)
fun glVertexAttribPointer(
    index: Int,
    size: Int,
    type: GLType,
    normalized: Boolean = false,
    stride: Int = 0,
    pointer: Long = 0L,
) {
    GL46C.glVertexAttribPointer(index, size, type.glValue, normalized, stride, pointer)
    glLog(StateChanged, VArray, "setVertexAttribPointer($index, $size, $type, $normalized, $stride, $pointer)")
    glCheckForErrors()
}

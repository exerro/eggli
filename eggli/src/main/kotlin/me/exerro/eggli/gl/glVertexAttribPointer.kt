package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.StateChanged
import me.exerro.eggli.GLDebugger.LogEntity.VArray
import me.exerro.eggli.enum.GLType
import me.exerro.eggli.types.GLAttributeIndex
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext, GLDebugger.Context)
fun glVertexAttribPointer(
    index: GLAttributeIndex,
    size: Int,
    type: GLType,
    normalized: Boolean = false,
    stride: Int = 0,
    pointer: Long = 0L,
) {
    glLog(StateChanged, VArray, "glVertexAttribPointer($index, $size, $type, $normalized, $stride, $pointer)")
    GL46C.glVertexAttribPointer(index, size, type.glValue, normalized, stride, pointer)
    glCheckForErrors()
}

package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.StateChanged
import me.exerro.eggli.GLDebugger.LogEntity.VArray
import me.exerro.eggli.types.GLVertexArray
import me.exerro.eggli.types.GLAttributeIndex
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext, GLDebugger.Context)
fun glEnableVertexAttribArray(vao: GLVertexArray, index: GLAttributeIndex) {
    GL46C.glEnableVertexArrayAttrib(vao.get(), index)
    glLog(StateChanged, VArray, "Enabled vertex attribute array $index for vertex array $vao")
    glCheckForErrors()
}

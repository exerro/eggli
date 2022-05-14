package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.ObjectBound
import me.exerro.eggli.GLDebugger.LogAction.ObjectUnbound
import me.exerro.eggli.GLDebugger.LogEntity.VArray
import me.exerro.eggli.types.GLShader
import me.exerro.eggli.types.GLShaderProgram
import me.exerro.eggli.types.GLVertexArray
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext, GLDebugger.Context)
fun glBindVertexArray(vao: GLVertexArray) {
    glLog(ObjectBound, VArray, "Binding vertex array $vao")
    GL46C.glBindVertexArray(vao.get())
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun glBindVertexArray() {
    glLog(ObjectUnbound, VArray, "Unbinding vertex array")
    GL46C.glBindVertexArray(0)
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun <T> glBindVertexArray(vao: GLVertexArray, block: () -> T): T {
    glLog(ObjectBound, VArray, "Binding vertex array $vao")
    GL46C.glBindVertexArray(vao.get())
    glCheckForErrors()

    try {
        return block()
    }
    finally {
        glLog(ObjectUnbound, VArray, "Unbinding vertex array")
        GL46C.glBindVertexArray(0)
        glCheckForErrors()
    }
}

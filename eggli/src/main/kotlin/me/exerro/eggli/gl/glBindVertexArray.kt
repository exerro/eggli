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
    GL46C.glBindVertexArray(vao.get())
    glLog(ObjectBound, VArray, "Bound vertex array $vao")
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun glBindVertexArray() {
    GL46C.glBindVertexArray(0)
    glLog(ObjectUnbound, VArray, "Unbound vertex array")
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun <T> glBindVertexArray(vao: GLVertexArray, block: () -> T): T {
    GL46C.glBindVertexArray(vao.get())
    glLog(ObjectBound, VArray, "Bound vertex array $vao")
    glCheckForErrors()

    try {
        return block()
    }
    finally {
        GL46C.glBindVertexArray(0)
        glLog(ObjectUnbound, VArray, "Unbound vertex array")
        glCheckForErrors()
    }
}

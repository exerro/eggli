package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.ObjectBound
import me.exerro.eggli.GLDebugger.LogAction.ObjectUnbound
import me.exerro.eggli.GLDebugger.LogEntity.Program
import me.exerro.eggli.types.GLShader
import me.exerro.eggli.types.GLShaderProgram
import me.exerro.eggli.types.GLVertexArray
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext, GLDebugger.Context)
fun glUseProgram(program: GLShaderProgram) {
    glLog(ObjectBound, Program, "Binding shader program $program")
    GL46C.glUseProgram(program.get())
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun glUseProgram() {
    glLog(ObjectUnbound, Program, "Unbinding shader program")
    GL46C.glUseProgram(0)
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun <T> glUseProgram(program: GLShaderProgram, block: () -> T): T {
    glLog(ObjectBound, Program, "Binding shader program $program")
    GL46C.glUseProgram(program.get())
    glCheckForErrors()

    try {
        return block()
    }
    finally {
        glLog(ObjectUnbound, Program, "Unbinding shader program")
        GL46C.glUseProgram(0)
        glCheckForErrors()
    }
}

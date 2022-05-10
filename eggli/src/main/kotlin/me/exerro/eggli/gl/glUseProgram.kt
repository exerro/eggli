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
    GL46C.glUseProgram(program.get())
    glLog(ObjectBound, Program, "Bound shader program $program")
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun glUseProgram() {
    GL46C.glUseProgram(0)
    glLog(ObjectUnbound, Program, "Unbound shader program")
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun <T> glUseProgram(program: GLShaderProgram, block: () -> T): T {
    GL46C.glUseProgram(program.get())
    glLog(ObjectBound, Program, "Bound shader program $program")
    glCheckForErrors()

    try {
        return block()
    }
    finally {
        GL46C.glUseProgram(0)
        glLog(ObjectUnbound, Program, "Unbound shader program")
        glCheckForErrors()
    }
}

package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.Error
import me.exerro.eggli.GLDebugger.LogAction.Generic
import me.exerro.eggli.GLDebugger.LogEntity.Program
import me.exerro.eggli.types.GLShader
import me.exerro.eggli.types.GLShaderProgram
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext, GLDebugger.Context)
fun glLinkProgram(program: GLShaderProgram, check: Boolean = true) {
    val programId = program.get()

    GL46C.glLinkProgram(programId)
    glLog(Generic, Program, "Linked program $program")

    if (check && GL46C.glGetProgrami(programId, GL46C.GL_LINK_STATUS) != GL46C.GL_TRUE) {
        val message = "Program link error:\n${GL46C.glGetProgramInfoLog(programId)}"
        glLog(Error, Program, message)
    }

    glCheckForErrors()
}

package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.StateChanged
import me.exerro.eggli.GLDebugger.LogEntity.Program
import me.exerro.eggli.types.GLShader
import me.exerro.eggli.types.GLShaderProgram
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext, GLDebugger.Context)
fun glAttachShader(program: GLShaderProgram, shader: GLShader) {
    GL46C.glAttachShader(program.get(), shader.get())
    glLog(StateChanged, Program, "Attached shader $shader to program $program")
    glCheckForErrors()
}

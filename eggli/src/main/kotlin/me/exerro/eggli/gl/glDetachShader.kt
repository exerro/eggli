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
fun glDetachShader(program: GLShaderProgram, shader: GLShader) {
    glLog(StateChanged, Program, "Detaching shader $shader from program $program")
    GL46C.glDetachShader(program.get(), shader.get())
    glCheckForErrors()
}

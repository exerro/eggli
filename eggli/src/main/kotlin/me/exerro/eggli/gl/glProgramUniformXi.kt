package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.types.GLShaderProgram
import me.exerro.eggli.types.GLUniformLocation
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext)
fun glProgramUniform1i(program: GLShaderProgram, location: GLUniformLocation, v0: Boolean) {
    GL46C.glProgramUniform1i(program.get(), location, if (v0) 1 else 0)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
fun glProgramUniform1i(program: GLShaderProgram, location: GLUniformLocation, v0: Int) {
    GL46C.glProgramUniform1i(program.get(), location, v0)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
fun glProgramUniform2i(program: GLShaderProgram, location: GLUniformLocation, v0: Int, v1: Int) {
    GL46C.glProgramUniform2i(program.get(), location, v0, v1)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
fun glProgramUniform3i(program: GLShaderProgram, location: GLUniformLocation, v0: Int, v1: Int, v2: Int) {
    GL46C.glProgramUniform3i(program.get(), location, v0, v1, v2)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
fun glProgramUniform4i(program: GLShaderProgram, location: GLUniformLocation, v0: Int, v1: Int, v2: Int, v3: Int) {
    GL46C.glProgramUniform4i(program.get(), location, v0, v1, v2, v3)
    glCheckForErrors()
}

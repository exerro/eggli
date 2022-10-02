package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.types.GLShaderProgram
import me.exerro.eggli.types.GLUniformLocation
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext)
fun glProgramUniform1f(program: GLShaderProgram, location: GLUniformLocation, v0: Float) {
    GL46C.glProgramUniform1f(program.get(), location, v0)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
fun glProgramUniform2f(program: GLShaderProgram, location: GLUniformLocation, v0: Float, v1: Float) {
    GL46C.glProgramUniform2f(program.get(), location, v0, v1)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
fun glProgramUniform3f(program: GLShaderProgram, location: GLUniformLocation, v0: Float, v1: Float, v2: Float) {
    GL46C.glProgramUniform3f(program.get(), location, v0, v1, v2)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
fun glProgramUniform4f(program: GLShaderProgram, location: GLUniformLocation, v0: Float, v1: Float, v2: Float, v3: Float) {
    GL46C.glProgramUniform4f(program.get(), location, v0, v1, v2, v3)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
fun glProgramUniform1fv(program: GLShaderProgram, location: GLUniformLocation, values: FloatArray) {
    GL46C.glProgramUniform1fv(program.get(), location, values)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
fun glProgramUniform2fv(program: GLShaderProgram, location: GLUniformLocation, values: FloatArray) {
    GL46C.glProgramUniform2fv(program.get(), location, values)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
fun glProgramUniform3fv(program: GLShaderProgram, location: GLUniformLocation, values: FloatArray) {
    GL46C.glProgramUniform3fv(program.get(), location, values)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
fun glProgramUniform4fv(program: GLShaderProgram, location: GLUniformLocation, values: FloatArray) {
    GL46C.glProgramUniform4fv(program.get(), location, values)
    glCheckForErrors()
}

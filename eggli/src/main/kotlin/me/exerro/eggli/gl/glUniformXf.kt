package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.types.GLUniformLocation
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext)
@Deprecated("Prefer using glProgramUniform1f", replaceWith = ReplaceWith("glProgramUniform1f(program, location, v0)"))
fun glUniform1f(location: GLUniformLocation, v0: Float) {
    GL46C.glUniform1f(location, v0)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
@Deprecated("Prefer using glProgramUniform2f", replaceWith = ReplaceWith("glProgramUniform2f(program, location, v0, v1)"))
fun glUniform2f(location: GLUniformLocation, v0: Float, v1: Float) {
    GL46C.glUniform2f(location, v0, v1)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
@Deprecated("Prefer using glProgramUniform3f", replaceWith = ReplaceWith("glProgramUniform3f(program, location, v0, v1, v2)"))
fun glUniform3f(location: GLUniformLocation, v0: Float, v1: Float, v2: Float) {
    GL46C.glUniform3f(location, v0, v1, v2)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
@Deprecated("Prefer using glProgramUniform4f", replaceWith = ReplaceWith("glProgramUniform4f(program, location, v0, v1, v2, v3)"))
fun glUniform4f(location: GLUniformLocation, v0: Float, v1: Float, v2: Float, v3: Float) {
    GL46C.glUniform4f(location, v0, v1, v2, v3)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
@Deprecated("Prefer using glProgramUniform1fv", replaceWith = ReplaceWith("glProgramUniform1fv(program, location, values)"))
fun glUniform1fv(location: GLUniformLocation, values: FloatArray) {
    GL46C.glUniform1fv(location, values)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
@Deprecated("Prefer using glProgramUniform2fv", replaceWith = ReplaceWith("glProgramUniform2fv(program, location, values)"))
fun glUniform2fv(location: GLUniformLocation, values: FloatArray) {
    GL46C.glUniform2fv(location, values)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
@Deprecated("Prefer using glProgramUniform3fv", replaceWith = ReplaceWith("glProgramUniform3fv(program, location, values)"))
fun glUniform3fv(location: GLUniformLocation, values: FloatArray) {
    GL46C.glUniform3fv(location, values)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
@Deprecated("Prefer using glProgramUniform4fv", replaceWith = ReplaceWith("glProgramUniform4fv(program, location, values)"))
fun glUniform4fv(location: GLUniformLocation, values: FloatArray) {
    GL46C.glUniform4fv(location, values)
    glCheckForErrors()
}

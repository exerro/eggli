package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.types.GLUniformLocation
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext)
@Deprecated("Prefer using glProgramUniform1i", replaceWith = ReplaceWith("glProgramUniform1i(program, location, v0)"))
fun glUniform1i(location: GLUniformLocation, v0: Boolean) {
    GL46C.glUniform1i(location, if (v0) 1 else 0)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
@Deprecated("Prefer using glProgramUniform1i", replaceWith = ReplaceWith("glProgramUniform1i(program, location, v0)"))
fun glUniform1i(location: GLUniformLocation, v0: Int) {
    GL46C.glUniform1i(location, v0)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
@Deprecated("Prefer using glProgramUniform2i", replaceWith = ReplaceWith("glProgramUniform2i(program, location, v0, v1)"))
fun glUniform2i(location: GLUniformLocation, v0: Int, v1: Int) {
    GL46C.glUniform2i(location, v0, v1)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
@Deprecated("Prefer using glProgramUniform3i", replaceWith = ReplaceWith("glProgramUniform3i(program, location, v0, v1, v2)"))
fun glUniform3i(location: GLUniformLocation, v0: Int, v1: Int, v2: Int) {
    GL46C.glUniform3i(location, v0, v1, v2)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
@Deprecated("Prefer using glProgramUniform4i", replaceWith = ReplaceWith("glProgramUniform4i(program, location, v0, v1, v2, v3)"))
fun glUniform4i(location: GLUniformLocation, v0: Int, v1: Int, v2: Int, v3: Int) {
    GL46C.glUniform4i(location, v0, v1, v2, v3)
    glCheckForErrors()
}

package me.exerro.eggli.gl

import me.exerro.eggli.GL
import me.exerro.eggli.GLResource
import me.exerro.eggli.types.GLShaderProgram
import me.exerro.lifetimes.Lifetime
import org.lwjgl.opengl.GL46C
import org.lwjgl.opengl.KHRDebug

/** TODO */
context (Lifetime)
fun glCreateProgram(label: String? = null): GL<GLShaderProgram> = GL {
    val programId = GL46C.glCreateProgram()
    if (label != null) KHRDebug.glObjectLabel(GL46C.GL_PROGRAM, programId, label)
    glCheckForErrors()
    GLResource(programId) {
        GL46C.glDeleteProgram(it)
        glCheckForErrors()
    }
}

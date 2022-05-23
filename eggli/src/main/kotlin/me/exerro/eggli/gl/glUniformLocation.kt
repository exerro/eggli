package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.types.GLShaderProgram
import me.exerro.eggli.types.GLUniformLocation
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext)
fun glGetUniformLocation(program: GLShaderProgram, name: CharSequence): GLUniformLocation {
    val programId = program.get()
    val location = GL46C.glGetUniformLocation(programId, name)
    glCheckForErrors()

    return location
}

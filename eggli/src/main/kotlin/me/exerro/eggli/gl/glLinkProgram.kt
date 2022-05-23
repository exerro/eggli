package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.types.GLShaderProgram
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext)
fun glLinkProgram(program: GLShaderProgram, validate: Boolean = true) {
    val programId = program.get()

    GL46C.glLinkProgram(programId)

    if (validate && GL46C.glGetProgrami(programId, GL46C.GL_LINK_STATUS) != GL46C.GL_TRUE) {
        error("Program did not link:\n${GL46C.glGetProgramInfoLog(programId)}")
    }

    glCheckForErrors()
}

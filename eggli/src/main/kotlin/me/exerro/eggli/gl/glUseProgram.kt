package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.types.GLShaderProgram
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext)
fun glUseProgram(program: GLShaderProgram) {
    GL46C.glUseProgram(program.get())
    glCheckForErrors()
}

/** TODO */
context (GLContext)
fun glUseProgram() {
    GL46C.glUseProgram(0)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
fun <T> glUseProgram(program: GLShaderProgram, block: () -> T): T {
    GL46C.glUseProgram(program.get())
    glCheckForErrors()

    try {
        return block()
    }
    finally {
        GL46C.glUseProgram(0)
        glCheckForErrors()
    }
}

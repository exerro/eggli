package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.types.GLShader
import me.exerro.eggli.types.GLShaderProgram
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext)
fun glAttachShader(program: GLShaderProgram, shader: GLShader) {
    GL46C.glAttachShader(program.get(), shader.get())
    glCheckForErrors()
}

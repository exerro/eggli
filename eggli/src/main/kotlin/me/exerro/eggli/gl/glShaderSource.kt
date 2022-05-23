package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.types.GLShader
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext)
fun glShaderSource(shader: GLShader, source: CharSequence) {
    GL46C.glShaderSource(shader.get(), source)
    glCheckForErrors()
}

package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.types.GLShader
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext)
fun glCompileShader(shader: GLShader, validate: Boolean = true) {
    val shaderId = shader.get()

    GL46C.glCompileShader(shaderId)

    if (validate && GL46C.glGetShaderi(shaderId, GL46C.GL_COMPILE_STATUS) != GL46C.GL_TRUE) {
        val message = "Shader compilation error:\n"
        error("Shader did not compile:\n${GL46C.glGetShaderInfoLog(shaderId)}")
    }

    glCheckForErrors()
}

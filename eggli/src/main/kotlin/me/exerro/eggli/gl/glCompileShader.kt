package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.Error
import me.exerro.eggli.GLDebugger.LogAction.Generic
import me.exerro.eggli.GLDebugger.LogEntity.Shader
import me.exerro.eggli.types.GLShader
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext, GLDebugger.Context)
fun glCompileShader(shader: GLShader, validate: Boolean = true) {
    val shaderId = shader.get()

    GL46C.glCompileShader(shaderId)
    glLog(Generic, Shader, "Compiled shader $shader")

    if (validate && GL46C.glGetShaderi(shaderId, GL46C.GL_COMPILE_STATUS) != GL46C.GL_TRUE) {
        val message = "Shader compilation error:\n${GL46C.glGetShaderInfoLog(shaderId)}"
        glLog(Error, Shader, message)
    }

    glCheckForErrors()
}

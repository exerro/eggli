package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.Generic
import me.exerro.eggli.GLDebugger.LogEntity.Shader
import me.exerro.eggli.types.GLShader
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext, GLDebugger.Context)
fun glShaderSource(shader: GLShader, source: CharSequence) {
    glLog(Generic, Shader, "Setting shader source for shader $shader to\n$source")
    GL46C.glShaderSource(shader.get(), source)
    glCheckForErrors()
}

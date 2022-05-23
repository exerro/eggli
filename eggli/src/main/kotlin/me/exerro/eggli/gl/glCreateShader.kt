package me.exerro.eggli.gl

import me.exerro.eggli.GL
import me.exerro.eggli.GLResource
import me.exerro.eggli.enum.GLShaderType
import me.exerro.eggli.types.GLShader
import me.exerro.lifetimes.Lifetime
import org.lwjgl.opengl.GL46C
import org.lwjgl.opengl.KHRDebug

/** TODO */
context (Lifetime)
fun glCreateShader(type: GLShaderType, label: String? = null): GL<GLShader> = GL {
    val shaderId = GL46C.glCreateShader(type.glValue)
    if (label != null) KHRDebug.glObjectLabel(GL46C.GL_DEBUG_SOURCE_APPLICATION, shaderId, label)
    glCheckForErrors()
    GLResource(shaderId) {
        GL46C.glDeleteShader(it)
        glCheckForErrors()
    }
}

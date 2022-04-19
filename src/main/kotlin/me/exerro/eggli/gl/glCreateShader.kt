package me.exerro.eggli.gl

import me.exerro.eggli.GL
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.ObjectCreated
import me.exerro.eggli.GLDebugger.LogAction.ObjectDestroyed
import me.exerro.eggli.GLDebugger.LogEntity.Shader
import me.exerro.eggli.GLResource
import me.exerro.eggli.enum.GLShaderType
import me.exerro.eggli.types.GLShader
import me.exerro.lifetimes.Lifetime
import org.lwjgl.opengl.GL46C

/** TODO */
context (Lifetime, GLDebugger.Context)
fun glCreateShader(type: GLShaderType): GL<GLShader> = GL {
    val shaderId = GL46C.glCreateShader(type.glValue)
    glLog(ObjectCreated, Shader, "Created shader $shaderId of type $type")
    glCheckForErrors()
    GLResource(shaderId) {
        GL46C.glDeleteShader(it)
        glLog(ObjectDestroyed, Shader, "Destroyed shader $shaderId of type $type")
        glCheckForErrors()
    }
}

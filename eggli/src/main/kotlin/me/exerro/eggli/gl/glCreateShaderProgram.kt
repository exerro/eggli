package me.exerro.eggli.gl

import me.exerro.eggli.GL
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.ObjectCreated
import me.exerro.eggli.GLDebugger.LogAction.ObjectDestroyed
import me.exerro.eggli.GLDebugger.LogEntity.Program
import me.exerro.eggli.GLResource
import me.exerro.eggli.types.GLShaderProgram
import me.exerro.lifetimes.Lifetime
import org.lwjgl.opengl.GL46C

/** TODO */
context (Lifetime, GLDebugger.Context)
fun glCreateProgram(): GL<GLShaderProgram> = GL {
    val programId = GL46C.glCreateProgram()
    glLog(ObjectCreated, Program, "Created shader program $programId")
    glCheckForErrors()
    GLResource(programId) {
        glLog(ObjectDestroyed, Program, "Destroying shader program $programId")
        GL46C.glDeleteProgram(it)
        glCheckForErrors()
    }
}

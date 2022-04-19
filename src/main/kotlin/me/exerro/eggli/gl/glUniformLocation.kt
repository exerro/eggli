package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.Info
import me.exerro.eggli.GLDebugger.LogEntity.Shader
import me.exerro.eggli.types.GLShaderProgram
import me.exerro.eggli.types.GLUniformLocation
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext, GLDebugger.Context)
fun glGetUniformLocation(program: GLShaderProgram, name: CharSequence): GLUniformLocation {
    val programId = program.get()
    val location = GL46C.glGetUniformLocation(programId, name)
    glLog(Info, Shader, "Uniform location of '$name' in shader program $program is $location")
    glCheckForErrors()

    return location
}

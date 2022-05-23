package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.UniformChanged
import me.exerro.eggli.GLDebugger.LogEntity.Shader
import me.exerro.eggli.types.GLUniformLocation
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext, GLDebugger.Context)
fun glUniform1i(location: GLUniformLocation, v0: Boolean) {
    glLog(UniformChanged, Shader, "Setting uniform $location to $v0")
    GL46C.glUniform1i(location, if (v0) 1 else 0)
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun glUniform1i(location: GLUniformLocation, v0: Int) {
    glLog(UniformChanged, Shader, "Setting uniform $location to $v0")
    GL46C.glUniform1i(location, v0)
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun glUniform2i(location: GLUniformLocation, v0: Int, v1: Int) {
    glLog(UniformChanged, Shader, "Setting uniform $location to ($v0, $v1)")
    GL46C.glUniform2i(location, v0, v1)
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun glUniform3i(location: GLUniformLocation, v0: Int, v1: Int, v2: Int) {
    glLog(UniformChanged, Shader, "Setting uniform $location to ($v0, $v1, $v2)")
    GL46C.glUniform3i(location, v0, v1, v2)
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun glUniform4i(location: GLUniformLocation, v0: Int, v1: Int, v2: Int, v3: Int) {
    glLog(UniformChanged, Shader, "Setting uniform $location to ($v0, $v1, $v2, $v3)")
    GL46C.glUniform4i(location, v0, v1, v2, v3)
    glCheckForErrors()
}

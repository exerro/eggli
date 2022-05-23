package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.UniformChanged
import me.exerro.eggli.GLDebugger.LogEntity.Shader
import me.exerro.eggli.types.GLUniformLocation
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext, GLDebugger.Context)
fun glUniform1f(location: GLUniformLocation, v0: Float) {
    glLog(UniformChanged, Shader, "Setting uniform $location to $v0")
    GL46C.glUniform1f(location, v0)
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun glUniform2f(location: GLUniformLocation, v0: Float, v1: Float) {
    glLog(UniformChanged, Shader, "Setting uniform $location to ($v0, $v1)")
    GL46C.glUniform2f(location, v0, v1)
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun glUniform3f(location: GLUniformLocation, v0: Float, v1: Float, v2: Float) {
    glLog(UniformChanged, Shader, "Setting uniform $location to ($v0, $v1, $v2)")
    GL46C.glUniform3f(location, v0, v1, v2)
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun glUniform4f(location: GLUniformLocation, v0: Float, v1: Float, v2: Float, v3: Float) {
    glLog(UniformChanged, Shader, "Setting uniform $location to ($v0, $v1, $v2, $v3)")
    GL46C.glUniform4f(location, v0, v1, v2, v3)
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun glUniform1fv(location: GLUniformLocation, values: FloatArray) {
    GL46C.glUniform1fv(location, values)
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun glUniform2fv(location: GLUniformLocation, values: FloatArray) {
    GL46C.glUniform2fv(location, values)
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun glUniform3fv(location: GLUniformLocation, values: FloatArray) {
    GL46C.glUniform3fv(location, values)
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun glUniform4fv(location: GLUniformLocation, values: FloatArray) {
    GL46C.glUniform4fv(location, values)
    glCheckForErrors()
}

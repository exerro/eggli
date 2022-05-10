package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.UniformChanged
import me.exerro.eggli.GLDebugger.LogEntity.Shader
import me.exerro.eggli.types.GLUniformLocation
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext, GLDebugger.Context)
fun glUniformMatrix2fv(location: GLUniformLocation, values: FloatArray, transpose: Boolean = true) {
    GL46C.glUniformMatrix2fv(location, transpose, values)
    glLog(UniformChanged, Shader, "Set uniform $location (transpose=$transpose) to $values")
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun glUniformMatrix3fv(location: GLUniformLocation, values: FloatArray, transpose: Boolean = true) {
    GL46C.glUniformMatrix3fv(location, transpose, values)
    glLog(UniformChanged, Shader, "Set uniform $location (transpose=$transpose) to $values")
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun glUniformMatrix4fv(location: GLUniformLocation, values: FloatArray, transpose: Boolean = true) {
    GL46C.glUniformMatrix4fv(location, transpose, values)
    glLog(UniformChanged, Shader, "Set uniform $location (transpose=$transpose) to $values")
    glCheckForErrors()
}

package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.types.GLUniformLocation
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext)
fun glUniformMatrix2fv(location: GLUniformLocation, values: FloatArray, transpose: Boolean = true) {
    GL46C.glUniformMatrix2fv(location, transpose, values)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
fun glUniformMatrix3fv(location: GLUniformLocation, values: FloatArray, transpose: Boolean = true) {
    GL46C.glUniformMatrix3fv(location, transpose, values)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
fun glUniformMatrix4fv(location: GLUniformLocation, values: FloatArray, transpose: Boolean = true) {
    GL46C.glUniformMatrix4fv(location, transpose, values)
    glCheckForErrors()
}

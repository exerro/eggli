package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.GLDrawMode
import me.exerro.eggli.enum.GL_TRIANGLES
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext)
fun glDrawElements(
    mode: GLDrawMode = GL_TRIANGLES,
    count: Int,
    type: Int = GL46C.GL_UNSIGNED_INT, // TODO!
    indices: Long = 0L
) {
    GL46C.glDrawElements(mode.glValue, count, type, indices)
    glCheckForErrors()
}

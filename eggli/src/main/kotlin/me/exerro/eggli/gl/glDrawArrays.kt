package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.GLDrawMode
import me.exerro.eggli.enum.GL_TRIANGLES
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext)
fun glDrawArrays(
    mode: GLDrawMode = GL_TRIANGLES,
    first: Int = 0,
    count: Int,
) {
    GL46C.glDrawArrays(mode.glValue, first, count)
    glCheckForErrors()
}

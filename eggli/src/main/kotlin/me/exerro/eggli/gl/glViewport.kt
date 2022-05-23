package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import org.lwjgl.opengl.GL46C

/**
 * @see <a href="https://docs.gl/gl4/glViewport">Reference Page</a>
 */
context (GLContext)
fun glViewport(x: Int = 0, y: Int = 0, w: Int, h: Int) {
    GL46C.glViewport(x, y, w, h)
    glCheckForErrors()
}

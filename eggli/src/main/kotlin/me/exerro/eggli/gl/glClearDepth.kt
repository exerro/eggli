package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import org.lwjgl.opengl.GL46C

/**
 * @see <a href="https://docs.gl/gl4/glClearDepth">Reference Page</a>
 */
context (GLContext)
fun glClearDepth(depth: Float) {
    GL46C.glClearDepthf(depth)
    glCheckForErrors()
}

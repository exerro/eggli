package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import org.lwjgl.opengl.GL46C

/** @see <a href="https://docs.gl/gl4/glDepthMask">Reference Page</a> */
context (GLContext)
fun glDepthMask(flag: Boolean) {
    GL46C.glDepthMask(flag)
    glCheckForErrors()
}

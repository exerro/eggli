package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.GLDepthFunction
import org.lwjgl.opengl.GL46C

/** @see <a href="https://docs.gl/gl4/glDepthFunc">Reference Page</a> */
context (GLContext)
fun glDepthFunc(func: GLDepthFunction) {
    GL46C.glDepthFunc(func.glValue)
    glCheckForErrors()
}

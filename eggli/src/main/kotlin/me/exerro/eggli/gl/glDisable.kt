package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.GLOption
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext)
fun glDisable(target: GLOption) {
    GL46C.glDisable(target.glValue)
    glCheckForErrors()
}

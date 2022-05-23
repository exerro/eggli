package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.GLClearBit
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext)
fun glClear(clearBits: GLClearBit) {
    GL46C.glClear(clearBits.glValue)
    glCheckForErrors()
}

package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.GLBufferTarget
import me.exerro.eggli.types.GLBuffer
import org.lwjgl.opengl.GL46C

/** @see <a href="https://docs.gl/gl4/glDepthMask">Reference Page</a> */
context (GLContext)
fun glBindBufferBase(target: GLBufferTarget, index: Int, buffer: GLBuffer) {
    GL46C.glBindBufferBase(target.glValue, index, buffer.get())
    glCheckForErrors()
}

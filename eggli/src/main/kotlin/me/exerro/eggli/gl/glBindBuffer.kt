package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.GLBufferTarget
import me.exerro.eggli.types.GLBuffer
import org.lwjgl.opengl.GL46C

/** TODO
 *  https://www.khronos.org/opengl/wiki/GLAPI/glBindBuffer */
context (GLContext)
fun glBindBuffer(target: GLBufferTarget, vao: GLBuffer) {
    GL46C.glBindBuffer(target.glValue, vao.get())
    glCheckForErrors()
}

/** TODO */
context (GLContext)
fun glBindBuffer(target: GLBufferTarget) {
    GL46C.glBindBuffer(target.glValue, 0)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
fun <T> glBindBuffer(target: GLBufferTarget, vao: GLBuffer, block: () -> T): T {
    GL46C.glBindBuffer(target.glValue, vao.get())
    glCheckForErrors()

    try {
        return block()
    }
    finally {
        GL46C.glBindBuffer(target.glValue, 0)
        glCheckForErrors()
    }
}

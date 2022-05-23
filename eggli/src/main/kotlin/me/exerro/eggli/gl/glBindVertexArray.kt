package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.types.GLVertexArray
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext)
fun glBindVertexArray(vao: GLVertexArray) {
    GL46C.glBindVertexArray(vao.get())
    glCheckForErrors()
}

/** TODO */
context (GLContext)
fun glBindVertexArray() {
    GL46C.glBindVertexArray(0)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
fun <T> glBindVertexArray(vao: GLVertexArray, block: () -> T): T {
    GL46C.glBindVertexArray(vao.get())
    glCheckForErrors()

    try {
        return block()
    }
    finally {
        GL46C.glBindVertexArray(0)
        glCheckForErrors()
    }
}

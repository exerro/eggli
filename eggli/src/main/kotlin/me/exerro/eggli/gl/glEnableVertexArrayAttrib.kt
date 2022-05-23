package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.types.GLAttributeIndex
import me.exerro.eggli.types.GLVertexArray
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext)
fun glEnableVertexAttribArray(vao: GLVertexArray, index: GLAttributeIndex) {
    GL46C.glEnableVertexArrayAttrib(vao.get(), index)
    glCheckForErrors()
}

package me.exerro.eggli.gl

import me.exerro.eggli.GL
import me.exerro.eggli.GLResource
import me.exerro.eggli.types.GLVertexArray
import me.exerro.lifetimes.Lifetime
import org.lwjgl.opengl.GL46C
import org.lwjgl.opengl.KHRDebug

/** TODO */
context (Lifetime)
fun glGenVertexArrays(label: String? = null): GL<GLVertexArray> = GL {
    val vaoId = GL46C.glGenVertexArrays()
    if (label != null) KHRDebug.glObjectLabel(GL46C.GL_VERTEX_ARRAY, vaoId, label)
    glCheckForErrors()
    GLResource(vaoId) {
        GL46C.glDeleteVertexArrays(it)
        glCheckForErrors()
    }
}

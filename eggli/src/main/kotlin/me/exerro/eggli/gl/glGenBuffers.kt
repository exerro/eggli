package me.exerro.eggli.gl

import me.exerro.eggli.GL
import me.exerro.eggli.GLResource
import me.exerro.eggli.types.GLBuffer
import me.exerro.lifetimes.Lifetime
import org.lwjgl.opengl.GL46C
import org.lwjgl.opengl.KHRDebug

/** TODO */
context (Lifetime)
fun glGenBuffers(label: String? = null): GL<GLBuffer> = GL {
    val bufferId = GL46C.glGenBuffers()
    if (label != null) KHRDebug.glObjectLabel(GL46C.GL_DEBUG_SOURCE_APPLICATION, bufferId, label)
    glCheckForErrors()
    GLResource(bufferId) {
        GL46C.glDeleteBuffers(it)
        glCheckForErrors()
    }
}

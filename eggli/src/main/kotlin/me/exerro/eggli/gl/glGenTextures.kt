package me.exerro.eggli.gl

import me.exerro.eggli.GL
import me.exerro.eggli.GLResource
import me.exerro.eggli.types.GLTexture
import me.exerro.lifetimes.Lifetime
import org.lwjgl.opengl.GL46C
import org.lwjgl.opengl.KHRDebug

/** TODO */
context (Lifetime)
fun glGenTextures(label: String? = null): GL<GLTexture> = GL {
    val textureId = GL46C.glGenTextures()
    if (label != null) KHRDebug.glObjectLabel(GL46C.GL_DEBUG_SOURCE_APPLICATION, textureId, label)
    glCheckForErrors()
    GLResource(textureId) {
        GL46C.glDeleteTextures(it)
        glCheckForErrors()
    }
}

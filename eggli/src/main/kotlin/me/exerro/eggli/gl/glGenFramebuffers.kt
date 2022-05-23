package me.exerro.eggli.gl

import me.exerro.eggli.GL
import me.exerro.eggli.GLResource
import me.exerro.eggli.types.GLFramebuffer
import me.exerro.lifetimes.Lifetime
import org.lwjgl.opengl.GL46C
import org.lwjgl.opengl.KHRDebug

/** TODO */
context (Lifetime)
fun glGenFramebuffers(label: String? = null): GL<GLFramebuffer> = GL {
    val framebufferId = GL46C.glGenFramebuffers()
    if (label != null) KHRDebug.glObjectLabel(GL46C.GL_FRAMEBUFFER, framebufferId, label)
    glCheckForErrors()
    GLResource(framebufferId) {
        GL46C.glDeleteFramebuffers(it)
        glCheckForErrors()
    }
}

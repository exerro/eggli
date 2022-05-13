package me.exerro.eggli.gl

import me.exerro.eggli.GL
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.ObjectCreated
import me.exerro.eggli.GLDebugger.LogAction.ObjectDestroyed
import me.exerro.eggli.GLDebugger.LogEntity.FBuffer
import me.exerro.eggli.GLResource
import me.exerro.eggli.types.GLFramebuffer
import me.exerro.lifetimes.Lifetime
import org.lwjgl.opengl.GL46C

/** TODO */
context (Lifetime, GLDebugger.Context)
fun glGenFramebuffers(): GL<GLFramebuffer> = GL {
    val framebufferId = GL46C.glGenFramebuffers()
    glLog(ObjectCreated, FBuffer, "Created framebuffer $framebufferId")
    glCheckForErrors()
    GLResource(framebufferId) {
        GL46C.glDeleteFramebuffers(it)
        glLog(ObjectDestroyed, FBuffer, "Destroyed framebuffer $framebufferId")
        glCheckForErrors()
    }
}

package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.GLFramebufferStatus
import me.exerro.eggli.enum.GLFramebufferTarget
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext)
fun glCheckFramebufferStatus(target: GLFramebufferTarget): GLFramebufferStatus {
    val status = GLFramebufferStatus.fromGLValue(GL46C.glCheckFramebufferStatus(target.glValue))
    glCheckForErrors()
    return status
}

package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.Info
import me.exerro.eggli.GLDebugger.LogEntity.FBuffer
import me.exerro.eggli.enum.GLFramebufferStatus
import me.exerro.eggli.enum.GLFramebufferTarget
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext, GLDebugger.Context)
fun glCheckFramebufferStatus(target: GLFramebufferTarget): GLFramebufferStatus {
    val status = GLFramebufferStatus.fromGLValue(GL46C.glCheckFramebufferStatus(target.glValue))
    glLog(Info, FBuffer, "Status for framebuffer $target is $status")
    glCheckForErrors()
    return status
}

package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.ObjectBound
import me.exerro.eggli.GLDebugger.LogAction.ObjectUnbound
import me.exerro.eggli.GLDebugger.LogEntity.FBuffer
import me.exerro.eggli.enum.GLFramebufferTarget
import me.exerro.eggli.types.GLFramebuffer
import org.lwjgl.opengl.GL46C

/** TODO
 *  https://www.khronos.org/opengl/wiki/GLAPI/glBindFramebuffer */
context (GLContext, GLDebugger.Context)
fun glBindFramebuffer(target: GLFramebufferTarget, framebuffer: GLFramebuffer) {
    glLog(ObjectBound, FBuffer, "Binding framebuffer $framebuffer as $target")
    GL46C.glBindFramebuffer(target.glValue, framebuffer.get())
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun glBindFramebuffer(target: GLFramebufferTarget) {
    glLog(ObjectUnbound, FBuffer, "Unbinding framebuffer as $target")
    GL46C.glBindFramebuffer(target.glValue, 0)
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun <T> glBindFramebuffer(target: GLFramebufferTarget, framebuffer: GLFramebuffer, block: () -> T): T {
    glLog(ObjectBound, FBuffer, "Binding framebuffer $framebuffer as $target")
    GL46C.glBindFramebuffer(target.glValue, framebuffer.get())
    glCheckForErrors()

    try {
        return block()
    }
    finally {
        glLog(ObjectUnbound, FBuffer, "Unbinding framebuffer as $target")
        GL46C.glBindFramebuffer(target.glValue, 0)
        glCheckForErrors()
    }
}

package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.GLFramebufferTarget
import me.exerro.eggli.types.GLFramebuffer
import org.lwjgl.opengl.GL46C

/** TODO
 *  https://www.khronos.org/opengl/wiki/GLAPI/glBindFramebuffer */
context (GLContext)
fun glBindFramebuffer(target: GLFramebufferTarget, framebuffer: GLFramebuffer) {
    GL46C.glBindFramebuffer(target.glValue, framebuffer.get())
    glCheckForErrors()
}

/** TODO */
context (GLContext)
fun glBindFramebuffer(target: GLFramebufferTarget) {
    GL46C.glBindFramebuffer(target.glValue, 0)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
fun <T> glBindFramebuffer(target: GLFramebufferTarget, framebuffer: GLFramebuffer, block: () -> T): T {
    GL46C.glBindFramebuffer(target.glValue, framebuffer.get())
    glCheckForErrors()

    try {
        return block()
    }
    finally {
        GL46C.glBindFramebuffer(target.glValue, 0)
        glCheckForErrors()
    }
}

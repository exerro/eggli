package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.GLDrawBuffer
import me.exerro.eggli.types.GLFramebuffer
import org.lwjgl.opengl.GL46C

/** @see <a href="https://docs.gl/gl4/glDrawBuffers">Reference Page</a> */
context (GLContext)
fun glNamedFramebufferDrawBuffers(
    framebuffer: GLFramebuffer,
    buffers: Iterable<GLDrawBuffer>,
) {
    GL46C.glNamedFramebufferDrawBuffers(framebuffer.get(), buffers.map { it.glValue } .toIntArray())
    glCheckForErrors()
}

/** @see <a href="https://docs.gl/gl4/glDrawBuffers">Reference Page</a> */
context (GLContext)
fun glNamedFramebufferDrawBuffers(
    framebuffer: GLFramebuffer,
    vararg buffers: GLDrawBuffer,
) = glNamedFramebufferDrawBuffers(framebuffer, buffers.toList())

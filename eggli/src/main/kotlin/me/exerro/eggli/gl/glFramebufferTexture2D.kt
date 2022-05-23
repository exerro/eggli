package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.*
import me.exerro.eggli.types.GLTexture
import org.lwjgl.opengl.GL46C

/** @see <a href="https://docs.gl/gl4/glFramebufferTexture">Reference Page</a> */
context (GLContext)
fun glFramebufferTexture2D(
    target: GLFramebufferTarget = GL_FRAMEBUFFER,
    attachment: GLColorAttachment,
    textureTarget: GLTextureTarget = GL_TEXTURE_2D,
    texture: GLTexture,
    level: Int = 0
) {
    GL46C.glFramebufferTexture2D(target.glValue, attachment.glValue, textureTarget.glValue, texture.get(), level)
    glCheckForErrors()
}

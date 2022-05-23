package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.Data
import me.exerro.eggli.GLDebugger.LogEntity.Texture
import me.exerro.eggli.enum.*
import me.exerro.eggli.types.GLTexture
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext, GLDebugger.Context)
fun glTextureStorage2D(
    texture: GLTexture,
    levels: Int = 1,
    internalFormat: GLTextureStorage2DInternalFormat = GL_RGBA8,
    width: Int,
    height: Int,
) {
    // TODO: the following are not supported: GL_DEPTH_COMPONENT, GL_DEPTH_STENCIL, GL_RED, GL_RG, GL_RGB, GL_RGBA
    glLog(Data, Texture, "glTextureStorage2D($texture, $levels, $internalFormat, $width, $height)")
    GL46C.glTextureStorage2D(texture.get(), levels, internalFormat.glValue, width, height)
    glCheckForErrors()
}

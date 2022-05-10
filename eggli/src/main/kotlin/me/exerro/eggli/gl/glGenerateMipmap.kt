package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.*
import me.exerro.eggli.GLDebugger.LogEntity.Texture
import me.exerro.eggli.enum.*
import org.lwjgl.opengl.GL46C

// TODO: constrain this type
/**
 * Generate mipmaps for a specified texture target.
 * @param target the target to which the texture whose mimaps to generate is bound. One of:
 * [GL_TEXTURE_1D],
 * [GL_TEXTURE_2D],
 * [GL_TEXTURE_3D],
 * [GL_TEXTURE_1D_ARRAY],
 * [GL_TEXTURE_2D_ARRAY],
 * [GL_TEXTURE_CUBE_MAP]
 */
context (GLContext, GLDebugger.Context)
fun glGenerateMipmap(
    target: GLTextureTarget,
) {
    glLog(StateChanged, Texture, "Generating mipmaps for texture $target")
    GL46C.glGenerateMipmap(target.glValue)
    glCheckForErrors()
}

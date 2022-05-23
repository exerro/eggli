package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.*
import org.lwjgl.opengl.GL46C

// TODO: constrain this type
/**
 * Generate mipmaps for a specified texture target.
 * @param target the target to which the texture whose mipmaps to generate is
 * bound. One of:
 * [GL_TEXTURE_1D],
 * [GL_TEXTURE_2D],
 * [GL_TEXTURE_3D],
 * [GL_TEXTURE_1D_ARRAY],
 * [GL_TEXTURE_2D_ARRAY],
 * [GL_TEXTURE_CUBE_MAP]
 */
context (GLContext)
fun glGenerateMipmap(
    target: GLTextureTarget,
) {
    GL46C.glGenerateMipmap(target.glValue)
    glCheckForErrors()
}

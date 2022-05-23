package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.GLCullFace
import me.exerro.eggli.enum.GLStencilAction
import org.lwjgl.opengl.GL46C

/**
 * @param sfail action to take if the stencil test fails.
 * @param dpfail action to take if the stencil test passes, but the depth test fails.
 * @param dppass action to take if both the stencil and the depth test pass.
 * @see <a href="https://docs.gl/gl4/glStencilOpSeparate">Reference Page</a>
 */
context (GLContext)
fun glStencilOpSeparate(face: GLCullFace, sfail: GLStencilAction, dpfail: GLStencilAction, dppass: GLStencilAction) {
    GL46C.glStencilOpSeparate(face.glValue, sfail.glValue, dpfail.glValue, dppass.glValue)
    glCheckForErrors()
}

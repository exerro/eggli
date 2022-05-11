package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.*
import me.exerro.eggli.GLDebugger.LogEntity.State
import me.exerro.eggli.GLDebugger.LogEntity.Texture
import me.exerro.eggli.enum.GLStencilAction
import me.exerro.eggli.enum.GLStencilFunction
import me.exerro.eggli.enum.GLTextureTarget
import me.exerro.eggli.types.GLTexture
import org.lwjgl.opengl.GL46C

/**
 * @param sfail action to take if the stencil test fails.
 * @param dpfail action to take if the stencil test passes, but the depth test fails.
 * @param dppass action to take if both the stencil and the depth test pass.
 * @see <a href="https://docs.gl/gl4/glDepthMask">Reference Page</a>
 */
context (GLContext, GLDebugger.Context)
fun glStencilOp(sfail: GLStencilAction, dpfail: GLStencilAction, dppass: GLStencilAction) {
    glLog(StateChanged, State, "Setting stencil op to (sfail = $sfail, dpfail = $dpfail, dppass = $dppass)")
    GL46C.glStencilOp(sfail.glValue, dpfail.glValue, dppass.glValue)
    glCheckForErrors()
}

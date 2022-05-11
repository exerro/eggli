package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.*
import me.exerro.eggli.GLDebugger.LogEntity.State
import me.exerro.eggli.GLDebugger.LogEntity.Texture
import me.exerro.eggli.enum.GLTextureTarget
import me.exerro.eggli.types.GLTexture
import org.lwjgl.opengl.GL46C

/** @see <a href="https://docs.gl/gl4/glDepthMask">Reference Page</a> */
context (GLContext, GLDebugger.Context)
fun glStencilMask(mask: Int) {
    glLog(StateChanged, State, "Setting stencil mask to $mask")
    GL46C.glStencilMask(mask)
    glCheckForErrors()
}

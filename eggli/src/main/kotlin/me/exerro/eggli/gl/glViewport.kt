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
 * @see <a href="https://docs.gl/gl4/glViewport">Reference Page</a>
 */
context (GLContext, GLDebugger.Context)
fun glViewport(x: Int, y: Int, w: Int, h: Int) {
    glLog(StateChanged, State, "Setting viewport to ($x, $y, $w, $h)")
    GL46C.glViewport(x, y, w, h)
    glCheckForErrors()
}

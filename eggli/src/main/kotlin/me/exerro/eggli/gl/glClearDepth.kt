package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.StateChanged
import me.exerro.eggli.GLDebugger.LogEntity.State
import org.lwjgl.opengl.GL46C

/**
 * @see <a href="https://docs.gl/gl4/glClearDepth">Reference Page</a>
 */
context (GLContext, GLDebugger.Context)
fun glClearDepth(depth: Float) {
    GL46C.glClearDepthf(depth)
    glCheckForErrors()
}

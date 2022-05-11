package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.StateChanged
import me.exerro.eggli.GLDebugger.LogEntity.State
import me.exerro.eggli.enum.GLWindingOrder
import me.exerro.eggli.enum.GL_CCW
import org.lwjgl.opengl.GL46C

/**
 * TODO
 * @see <a href="https://docs.gl/gl4/glFrontFace">Reference Page</a>
 */
context (GLContext, GLDebugger.Context)
fun glFrontFace(order: GLWindingOrder = GL_CCW) {
    glLog(StateChanged, State, "Setting front face to $order")
    GL46C.glFrontFace(order.glValue)
    glCheckForErrors()
}

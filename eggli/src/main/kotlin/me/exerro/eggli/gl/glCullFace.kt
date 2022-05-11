package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.StateChanged
import me.exerro.eggli.GLDebugger.LogEntity.State
import me.exerro.eggli.enum.GLCullFace
import me.exerro.eggli.enum.GL_BACK
import org.lwjgl.opengl.GL46C

/**
 * TODO
 * @see <a href="https://docs.gl/gl4/glCullFace">Reference Page</a>
 */
context (GLContext, GLDebugger.Context)
fun glCullFace(face: GLCullFace = GL_BACK) {
    glLog(StateChanged, State, "Culling face $face")
    GL46C.glCullFace(face.glValue)
    glCheckForErrors()
}

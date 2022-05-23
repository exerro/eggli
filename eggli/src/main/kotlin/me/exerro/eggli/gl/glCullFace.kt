package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.GLCullFace
import me.exerro.eggli.enum.GL_BACK
import org.lwjgl.opengl.GL46C

/**
 * TODO
 * @see <a href="https://docs.gl/gl4/glCullFace">Reference Page</a>
 */
context (GLContext)
fun glCullFace(face: GLCullFace = GL_BACK) {
    GL46C.glCullFace(face.glValue)
    glCheckForErrors()
}

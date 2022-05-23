package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.GLActiveTexture
import org.lwjgl.opengl.GL46C

/** Selects which texture unit subsequent texture state calls will affect. The
 *  number of texture units an implementation supports is implementation
 *  dependent.
 *  https://www.khronos.org/opengl/wiki/GLAPI/glActiveTexture */
context (GLContext)
fun glActiveTexture(texture: GLActiveTexture) {
    GL46C.glActiveTexture(texture.glValue)
    glCheckForErrors()
}

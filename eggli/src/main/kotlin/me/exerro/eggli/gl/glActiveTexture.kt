package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.StateChanged
import me.exerro.eggli.GLDebugger.LogEntity.Texture
import me.exerro.eggli.enum.GLActiveTexture
import org.lwjgl.opengl.GL46C

/** Selects which texture unit subsequent texture state calls will affect. The
 *  number of texture units an implementation supports is implementation
 *  dependent.
 *  https://www.khronos.org/opengl/wiki/GLAPI/glActiveTexture */
context (GLContext, GLDebugger.Context)
fun glActiveTexture(texture: GLActiveTexture) {
    glLog(StateChanged, Texture, "Setting active texture unit to $texture")
    GL46C.glActiveTexture(texture.glValue)
    glCheckForErrors()
}

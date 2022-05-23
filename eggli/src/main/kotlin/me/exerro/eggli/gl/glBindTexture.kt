package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.GLTextureTarget
import me.exerro.eggli.types.GLTexture
import org.lwjgl.opengl.GL46C

/** TODO
 *  https://www.khronos.org/opengl/wiki/GLAPI/glBindTexture */
context (GLContext)
fun glBindTexture(target: GLTextureTarget, texture: GLTexture) {
    GL46C.glBindTexture(target.glValue, texture.get())
    glCheckForErrors()
}

/** TODO */
context (GLContext)
fun glBindTexture(target: GLTextureTarget) {
    GL46C.glBindTexture(target.glValue, 0)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
fun <T> glBindTexture(target: GLTextureTarget, texture: GLTexture, block: () -> T): T {
    GL46C.glBindTexture(target.glValue, texture.get())
    glCheckForErrors()

    try {
        return block()
    }
    finally {
        GL46C.glBindTexture(target.glValue, 0)
        glCheckForErrors()
    }
}

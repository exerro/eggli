package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.ObjectBound
import me.exerro.eggli.GLDebugger.LogAction.ObjectUnbound
import me.exerro.eggli.GLDebugger.LogEntity.Texture
import me.exerro.eggli.enum.GLTextureTarget
import me.exerro.eggli.types.GLTexture
import org.lwjgl.opengl.GL46C

/** TODO
 *  https://www.khronos.org/opengl/wiki/GLAPI/glBindTexture */
context (GLContext, GLDebugger.Context)
fun glBindTexture(target: GLTextureTarget, texture: GLTexture) {
    GL46C.glBindTexture(target.glValue, texture.get())
    glLog(ObjectBound, Texture, "Bound texture $texture as $target")
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun glBindTexture(target: GLTextureTarget) {
    GL46C.glBindTexture(target.glValue, 0)
    glLog(ObjectUnbound, Texture, "Unbound texture as $target")
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun <T> glBindTexture(target: GLTextureTarget, texture: GLTexture, block: () -> T): T {
    GL46C.glBindTexture(target.glValue, texture.get())
    glLog(ObjectBound, Texture, "Bound texture $texture as $target")
    glCheckForErrors()

    try {
        return block()
    }
    finally {
        GL46C.glBindTexture(target.glValue, 0)
        glLog(ObjectUnbound, Texture, "Unbound texture as $target")
        glCheckForErrors()
    }
}

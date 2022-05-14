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
    glLog(ObjectBound, Texture, "Binding texture $texture as $target")
    GL46C.glBindTexture(target.glValue, texture.get())
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun glBindTexture(target: GLTextureTarget) {
    glLog(ObjectUnbound, Texture, "Unbinding texture as $target")
    GL46C.glBindTexture(target.glValue, 0)
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun <T> glBindTexture(target: GLTextureTarget, texture: GLTexture, block: () -> T): T {
    glLog(ObjectBound, Texture, "Binding texture $texture as $target")
    GL46C.glBindTexture(target.glValue, texture.get())
    glCheckForErrors()

    try {
        return block()
    }
    finally {
        glLog(ObjectUnbound, Texture, "Unbinding texture as $target")
        GL46C.glBindTexture(target.glValue, 0)
        glCheckForErrors()
    }
}

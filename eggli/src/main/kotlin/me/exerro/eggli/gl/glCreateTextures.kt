package me.exerro.eggli.gl

import me.exerro.eggli.GL
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.ObjectCreated
import me.exerro.eggli.GLDebugger.LogAction.ObjectDestroyed
import me.exerro.eggli.GLDebugger.LogEntity.Texture
import me.exerro.eggli.GLResource
import me.exerro.eggli.enum.GLTextureTarget
import me.exerro.eggli.types.GLTexture
import me.exerro.lifetimes.Lifetime
import org.lwjgl.opengl.GL46C

/** TODO */
context (Lifetime, GLDebugger.Context)
fun glCreateTextures(type: GLTextureTarget): GL<GLTexture> = GL {
    val textureId = GL46C.glCreateTextures(type.glValue)
    glLog(ObjectCreated, Texture, "Created texture $textureId")
    glCheckForErrors()
    GLResource(textureId) {
        glLog(ObjectDestroyed, Texture, "Destroying texture $textureId")
        GL46C.glDeleteTextures(it)
        glCheckForErrors()
    }
}

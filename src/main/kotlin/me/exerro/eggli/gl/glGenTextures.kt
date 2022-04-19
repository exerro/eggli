package me.exerro.eggli.gl

import me.exerro.eggli.GL
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLResource
import me.exerro.lifetimes.Lifetime
import org.lwjgl.opengl.GL46C

context (Lifetime, GLDebugger)
fun glGenTextures() = GL {
    val textureId = GL46C.glGenTextures()
    glLog(GLDebugger.LogAction.ObjectCreated, GLDebugger.LogEntity.Texture, "Created texture $textureId")
    GLResource(textureId) {
        GL46C.glDeleteTextures(it)
        glLog(GLDebugger.LogAction.ObjectDestroyed, GLDebugger.LogEntity.Texture, "Destroyed texture $textureId")
    }
}

package me.exerro.eggli.gl

import me.exerro.eggli.GL
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.ObjectCreated
import me.exerro.eggli.GLDebugger.LogAction.ObjectDestroyed
import me.exerro.eggli.GLDebugger.LogEntity.Buffer
import me.exerro.eggli.GLResource
import me.exerro.eggli.types.GLBuffer
import me.exerro.lifetimes.Lifetime
import org.lwjgl.opengl.GL46C

/** TODO */
context (Lifetime, GLDebugger.Context)
fun glCreateBuffers(): GL<GLBuffer> = GL {
    val bufferId = GL46C.glCreateBuffers()
    glLog(ObjectCreated, Buffer, "Created buffer $bufferId")
    glCheckForErrors()
    GLResource(bufferId) {
        glLog(ObjectDestroyed, Buffer, "Destroying buffer $bufferId")
        GL46C.glDeleteBuffers(it)
        glCheckForErrors()
    }
}

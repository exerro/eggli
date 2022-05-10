package me.exerro.eggli.gl

import me.exerro.eggli.GL
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.ObjectCreated
import me.exerro.eggli.GLDebugger.LogAction.ObjectDestroyed
import me.exerro.eggli.GLDebugger.LogEntity.VArray
import me.exerro.eggli.GLResource
import me.exerro.eggli.types.GLVertexArray
import me.exerro.lifetimes.Lifetime
import org.lwjgl.opengl.GL46C

/** TODO */
context (Lifetime, GLDebugger.Context)
fun glGenVertexArrays(): GL<GLVertexArray> = GL {
    val vaoId = GL46C.glGenVertexArrays()
    glLog(ObjectCreated, VArray, "Created vertex array $vaoId")
    glCheckForErrors()
    GLResource(vaoId) {
        GL46C.glDeleteVertexArrays(it)
        glLog(ObjectDestroyed, VArray, "Destroyed vertex array $vaoId")
        glCheckForErrors()
    }
}

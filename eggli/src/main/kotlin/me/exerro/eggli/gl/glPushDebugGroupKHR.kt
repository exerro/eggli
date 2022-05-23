package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import org.lwjgl.opengl.GL46C.GL_DEBUG_SOURCE_APPLICATION
import org.lwjgl.opengl.KHRDebug

context (GLContext)
fun glPushDebugGroupKHR(id: Int = 0, message: String) {
    KHRDebug.glPushDebugGroup(GL_DEBUG_SOURCE_APPLICATION, id, message)
}

context (GLContext)
fun glPushDebugGroupKHR(id: Int = 0, message: String, fn: () -> Unit) {
    KHRDebug.glPushDebugGroup(GL_DEBUG_SOURCE_APPLICATION, id, message)
    try { fn() }
    finally { KHRDebug.glPopDebugGroup() }
}

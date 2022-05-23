package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import org.lwjgl.opengl.GL46C.GL_DEBUG_SOURCE_APPLICATION
import org.lwjgl.opengl.KHRDebug

context (GLContext)
fun glPopDebugGroupKHR() {
    KHRDebug.glPopDebugGroup()
}

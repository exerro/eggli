package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import org.lwjgl.opengl.KHRDebug

context (GLContext)
fun glPopDebugGroupKHR() {
    KHRDebug.glPopDebugGroup()
}

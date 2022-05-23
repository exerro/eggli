package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import org.lwjgl.opengl.GL46C

/**
 * Specify the red, green, blue, and alpha values used by [glClear] to clear the
 * color buffers. Values specified by [glClearColor] are clamped to the range
 * [0,1].
 *
 * @see <a href="https://docs.gl/gl4/glClearColor">Reference Page</a>
 */
context (GLContext)
fun glClearColor(red: Float, green: Float, blue: Float, alpha: Float = 1f) {
    GL46C.glClearColor(red, green, blue, alpha)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
fun glClearColor(rgb: Float, alpha: Float = 1f) {
    GL46C.glClearColor(rgb, rgb, rgb, alpha)
    glCheckForErrors()
}

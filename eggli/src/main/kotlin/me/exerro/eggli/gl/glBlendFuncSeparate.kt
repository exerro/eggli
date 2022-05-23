package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.GLBlendFactor
import org.lwjgl.opengl.GL46C

/** @see <a href="https://docs.gl/gl4/glBlendFuncSeparate">Reference Page</a> */
context (GLContext)
fun glBlendFuncSeparate(sfactorRGB: GLBlendFactor, dfactorRGB: GLBlendFactor, sfactorA: GLBlendFactor, dfactorA: GLBlendFactor) {
    GL46C.glBlendFuncSeparate(sfactorRGB.glValue, dfactorRGB.glValue, sfactorA.glValue, dfactorA.glValue)
    glCheckForErrors()
}

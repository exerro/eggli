package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.StateChanged
import me.exerro.eggli.GLDebugger.LogEntity.State
import me.exerro.eggli.enum.GLBlendFactor
import org.lwjgl.opengl.GL46C

/** @see <a href="https://docs.gl/gl4/glBlendFuncSeparate">Reference Page</a> */
context (GLContext, GLDebugger.Context)
fun glBlendFuncSeparate(sfactorRGB: GLBlendFactor, dfactorRGB: GLBlendFactor, sfactorA: GLBlendFactor, dfactorA: GLBlendFactor) {
    glLog(StateChanged, State, "glBlendFuncSeparate($sfactorRGB, $dfactorRGB, $sfactorA, $dfactorA)")
    GL46C.glBlendFuncSeparate(sfactorRGB.glValue, dfactorRGB.glValue, sfactorA.glValue, dfactorA.glValue)
    glCheckForErrors()
}

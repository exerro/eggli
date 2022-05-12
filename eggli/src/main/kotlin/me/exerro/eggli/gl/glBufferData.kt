package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.Generic
import me.exerro.eggli.GLDebugger.LogEntity.Buffer
import me.exerro.eggli.enum.GLBufferTarget
import me.exerro.eggli.enum.GLBufferUsage
import org.lwjgl.opengl.GL46C

/** TODO
 *  https://www.khronos.org/opengl/wiki/GLAPI/glBufferData */
context (GLContext, GLDebugger.Context)
@Deprecated(
    message = "In many cases, glNamedBufferData is preferred",
    replaceWith = ReplaceWith("glNamedBufferData(buffer, size, usage)"),
    level = DeprecationLevel.WARNING,
)
fun glBufferData(target: GLBufferTarget, size: Long, usage: GLBufferUsage = GLBufferUsage.StaticDraw) {
    GL46C.glBufferData(target.glValue, size, usage.glValue)
    glLog(Generic, Buffer, "Allocating buffer data for target $target ($size bytes)")
    glCheckForErrors()
}

/** TODO
 *  https://www.khronos.org/opengl/wiki/GLAPI/glBufferData */
context (GLContext, GLDebugger.Context)
@Deprecated(
    message = "In many cases, glNamedBufferData is preferred",
    replaceWith = ReplaceWith("glNamedBufferData(buffer, data, usage)"),
    level = DeprecationLevel.WARNING,
)
fun glBufferData(target: GLBufferTarget, data: ShortArray, usage: GLBufferUsage = GLBufferUsage.StaticDraw) {
    GL46C.glBufferData(target.glValue, data, usage.glValue)
    glLog(Generic, Buffer, "Set buffer data for target $target")
    glCheckForErrors()
}

/** TODO
 *  https://www.khronos.org/opengl/wiki/GLAPI/glBufferData */
context (GLContext, GLDebugger.Context)
@Deprecated(
    message = "In many cases, glNamedBufferData is preferred",
    replaceWith = ReplaceWith("glNamedBufferData(buffer, data, usage)"),
    level = DeprecationLevel.WARNING,
)
fun glBufferData(target: GLBufferTarget, data: IntArray, usage: GLBufferUsage = GLBufferUsage.StaticDraw) {
    GL46C.glBufferData(target.glValue, data, usage.glValue)
    glLog(Generic, Buffer, "Set buffer data for target $target")
    glCheckForErrors()
}

/** TODO
 *  https://www.khronos.org/opengl/wiki/GLAPI/glBufferData */
context (GLContext, GLDebugger.Context)
@Deprecated(
    message = "In many cases, glNamedBufferData is preferred",
    replaceWith = ReplaceWith("glNamedBufferData(buffer, data, usage)"),
    level = DeprecationLevel.WARNING,
)
fun glBufferData(target: GLBufferTarget, data: LongArray, usage: GLBufferUsage = GLBufferUsage.StaticDraw) {
    GL46C.glBufferData(target.glValue, data, usage.glValue)
    glLog(Generic, Buffer, "Set buffer data for target $target")
    glCheckForErrors()
}

/** TODO
 *  https://www.khronos.org/opengl/wiki/GLAPI/glBufferData */
context (GLContext, GLDebugger.Context)
@Deprecated(
    message = "In many cases, glNamedBufferData is preferred",
    replaceWith = ReplaceWith("glNamedBufferData(buffer, data, usage)"),
    level = DeprecationLevel.WARNING,
)
fun glBufferData(target: GLBufferTarget, data: FloatArray, usage: GLBufferUsage = GLBufferUsage.StaticDraw) {
    GL46C.glBufferData(target.glValue, data, usage.glValue)
    glLog(Generic, Buffer, "Set buffer data for target $target")
    glCheckForErrors()
}

/** TODO
 *  https://www.khronos.org/opengl/wiki/GLAPI/glBufferData */
context (GLContext, GLDebugger.Context)
@Deprecated(
    message = "In many cases, glNamedBufferData is preferred",
    replaceWith = ReplaceWith("glNamedBufferData(buffer, data, usage)"),
    level = DeprecationLevel.WARNING,
)
fun glBufferData(target: GLBufferTarget, data: DoubleArray, usage: GLBufferUsage = GLBufferUsage.StaticDraw) {
    GL46C.glBufferData(target.glValue, data, usage.glValue)
    glLog(Generic, Buffer, "Set buffer data for target $target")
    glCheckForErrors()
}

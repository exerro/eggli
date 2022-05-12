package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.Generic
import me.exerro.eggli.GLDebugger.LogEntity.Buffer
import me.exerro.eggli.enum.GLBufferUsage
import me.exerro.eggli.types.GLBuffer
import org.lwjgl.opengl.GL46C

/** TODO
 *  https://www.khronos.org/opengl/wiki/GLAPI/glBufferData */
context (GLContext, GLDebugger.Context)
fun glNamedBufferData(buffer: GLBuffer, size: Long, usage: GLBufferUsage = GLBufferUsage.StaticDraw) {
    GL46C.glNamedBufferData(buffer.get(), size, usage.glValue)
    glLog(Generic, Buffer, "Allocating buffer data for buffer $buffer ($size bytes)")
    glCheckForErrors()
}

/** TODO
 *  https://www.khronos.org/opengl/wiki/GLAPI/glBufferData */
context (GLContext, GLDebugger.Context)
fun glNamedBufferData(buffer: GLBuffer, data: ShortArray, usage: GLBufferUsage = GLBufferUsage.StaticDraw) {
    GL46C.glNamedBufferData(buffer.get(), data, usage.glValue)
    glLog(Generic, Buffer, "Set buffer data for buffer $buffer")
    glCheckForErrors()
}

/** TODO
 *  https://www.khronos.org/opengl/wiki/GLAPI/glBufferData */
context (GLContext, GLDebugger.Context)
fun glNamedBufferData(buffer: GLBuffer, data: IntArray, usage: GLBufferUsage = GLBufferUsage.StaticDraw) {
    GL46C.glNamedBufferData(buffer.get(), data, usage.glValue)
    glLog(Generic, Buffer, "Set buffer data for buffer $buffer")
    glCheckForErrors()
}

/** TODO
 *  https://www.khronos.org/opengl/wiki/GLAPI/glBufferData */
context (GLContext, GLDebugger.Context)
fun glNamedBufferData(buffer: GLBuffer, data: LongArray, usage: GLBufferUsage = GLBufferUsage.StaticDraw) {
    GL46C.glNamedBufferData(buffer.get(), data, usage.glValue)
    glLog(Generic, Buffer, "Set buffer data for buffer $buffer")
    glCheckForErrors()
}

/** TODO
 *  https://www.khronos.org/opengl/wiki/GLAPI/glBufferData */
context (GLContext, GLDebugger.Context)
fun glNamedBufferData(buffer: GLBuffer, data: FloatArray, usage: GLBufferUsage = GLBufferUsage.StaticDraw) {
    glLog(Generic, Buffer, "Setting buffer data for buffer $buffer")
    GL46C.glNamedBufferData(buffer.get(), data, usage.glValue)
    glCheckForErrors()
}

/** TODO
 *  https://www.khronos.org/opengl/wiki/GLAPI/glBufferData */
context (GLContext, GLDebugger.Context)
fun glNamedBufferData(buffer: GLBuffer, data: DoubleArray, usage: GLBufferUsage = GLBufferUsage.StaticDraw) {
    GL46C.glNamedBufferData(buffer.get(), data, usage.glValue)
    glLog(Generic, Buffer, "Set buffer data for buffer $buffer")
    glCheckForErrors()
}

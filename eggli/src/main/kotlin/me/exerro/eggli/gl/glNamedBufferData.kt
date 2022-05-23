package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.Data
import me.exerro.eggli.GLDebugger.LogEntity.Buffer
import me.exerro.eggli.enum.GLBufferUsage
import me.exerro.eggli.types.GLBuffer
import org.lwjgl.opengl.GL46C

/** TODO
 *  https://www.khronos.org/opengl/wiki/GLAPI/glBufferData */
context (GLContext, GLDebugger.Context)
fun glNamedBufferData(buffer: GLBuffer, size: Long, usage: GLBufferUsage = GLBufferUsage.StaticDraw) {
    glLog(Data, Buffer, "Allocating buffer data for buffer $buffer ($size bytes)")
    GL46C.glNamedBufferData(buffer.get(), size, usage.glValue)
    glCheckForErrors()
}

/** TODO
 *  https://www.khronos.org/opengl/wiki/GLAPI/glBufferData */
context (GLContext, GLDebugger.Context)
fun glNamedBufferData(buffer: GLBuffer, data: ShortArray, usage: GLBufferUsage = GLBufferUsage.StaticDraw) {
    glLog(Data, Buffer, "Setting buffer data for buffer $buffer")
    GL46C.glNamedBufferData(buffer.get(), data, usage.glValue)
    glCheckForErrors()
}

/** TODO
 *  https://www.khronos.org/opengl/wiki/GLAPI/glBufferData */
context (GLContext, GLDebugger.Context)
fun glNamedBufferData(buffer: GLBuffer, data: IntArray, usage: GLBufferUsage = GLBufferUsage.StaticDraw) {
    glLog(Data, Buffer, "Setting buffer data for buffer $buffer")
    GL46C.glNamedBufferData(buffer.get(), data, usage.glValue)
    glCheckForErrors()
}

/** TODO
 *  https://www.khronos.org/opengl/wiki/GLAPI/glBufferData */
context (GLContext, GLDebugger.Context)
fun glNamedBufferData(buffer: GLBuffer, data: LongArray, usage: GLBufferUsage = GLBufferUsage.StaticDraw) {
    glLog(Data, Buffer, "Setting buffer data for buffer $buffer")
    GL46C.glNamedBufferData(buffer.get(), data, usage.glValue)
    glCheckForErrors()
}

/** TODO
 *  https://www.khronos.org/opengl/wiki/GLAPI/glBufferData */
context (GLContext, GLDebugger.Context)
fun glNamedBufferData(buffer: GLBuffer, data: FloatArray, usage: GLBufferUsage = GLBufferUsage.StaticDraw) {
    glLog(Data, Buffer, "Setting buffer data for buffer $buffer")
    GL46C.glNamedBufferData(buffer.get(), data, usage.glValue)
    glCheckForErrors()
}

/** TODO
 *  https://www.khronos.org/opengl/wiki/GLAPI/glBufferData */
context (GLContext, GLDebugger.Context)
fun glNamedBufferData(buffer: GLBuffer, data: DoubleArray, usage: GLBufferUsage = GLBufferUsage.StaticDraw) {
    glLog(Data, Buffer, "Setting buffer data for buffer $buffer")
    GL46C.glNamedBufferData(buffer.get(), data, usage.glValue)
    glCheckForErrors()
}

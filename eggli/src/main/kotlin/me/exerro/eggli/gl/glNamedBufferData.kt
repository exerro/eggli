package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.GLBufferUsage
import me.exerro.eggli.enum.GL_STATIC_DRAW
import me.exerro.eggli.types.GLBuffer
import org.lwjgl.opengl.GL46C

/** TODO
 *  https://www.khronos.org/opengl/wiki/GLAPI/glBufferData */
context (GLContext)
fun glNamedBufferData(buffer: GLBuffer, size: Long, usage: GLBufferUsage = GL_STATIC_DRAW) {
    GL46C.glNamedBufferData(buffer.get(), size, usage.glValue)
    glCheckForErrors()
}

/** TODO
 *  https://www.khronos.org/opengl/wiki/GLAPI/glBufferData */
context (GLContext)
fun glNamedBufferData(buffer: GLBuffer, data: ShortArray, usage: GLBufferUsage = GL_STATIC_DRAW) {
    GL46C.glNamedBufferData(buffer.get(), data, usage.glValue)
    glCheckForErrors()
}

/** TODO
 *  https://www.khronos.org/opengl/wiki/GLAPI/glBufferData */
context (GLContext)
fun glNamedBufferData(buffer: GLBuffer, data: IntArray, usage: GLBufferUsage = GL_STATIC_DRAW) {
    GL46C.glNamedBufferData(buffer.get(), data, usage.glValue)
    glCheckForErrors()
}

/** TODO
 *  https://www.khronos.org/opengl/wiki/GLAPI/glBufferData */
context (GLContext)
fun glNamedBufferData(buffer: GLBuffer, data: LongArray, usage: GLBufferUsage = GL_STATIC_DRAW) {
    GL46C.glNamedBufferData(buffer.get(), data, usage.glValue)
    glCheckForErrors()
}

/** TODO
 *  https://www.khronos.org/opengl/wiki/GLAPI/glBufferData */
context (GLContext)
fun glNamedBufferData(buffer: GLBuffer, data: FloatArray, usage: GLBufferUsage = GL_STATIC_DRAW) {
    GL46C.glNamedBufferData(buffer.get(), data, usage.glValue)
    glCheckForErrors()
}

/** TODO
 *  https://www.khronos.org/opengl/wiki/GLAPI/glBufferData */
context (GLContext)
fun glNamedBufferData(buffer: GLBuffer, data: DoubleArray, usage: GLBufferUsage = GL_STATIC_DRAW) {
    GL46C.glNamedBufferData(buffer.get(), data, usage.glValue)
    glCheckForErrors()
}

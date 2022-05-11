package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.ObjectBound
import me.exerro.eggli.GLDebugger.LogAction.ObjectUnbound
import me.exerro.eggli.GLDebugger.LogEntity.Buffer
import me.exerro.eggli.enum.GLBufferTarget
import me.exerro.eggli.types.GLBuffer
import me.exerro.eggli.types.GLVertexArray
import org.lwjgl.opengl.GL46C

/** TODO
 *  https://www.khronos.org/opengl/wiki/GLAPI/glBindBuffer */
context (GLContext, GLDebugger.Context)
fun glBindBuffer(target: GLBufferTarget, vao: GLBuffer) {
    glLog(ObjectBound, Buffer, "Binding buffer $vao as $target")
    GL46C.glBindBuffer(target.glValue, vao.get())
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun glBindBuffer(target: GLBufferTarget) {
    glLog(ObjectUnbound, Buffer, "Unbinding buffer as $target")
    GL46C.glBindBuffer(target.glValue, 0)
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun <T> glBindBuffer(target: GLBufferTarget, vao: GLBuffer, block: () -> T): T {
    glLog(ObjectBound, Buffer, "Binding buffer $vao as $target")
    GL46C.glBindBuffer(target.glValue, vao.get())
    glCheckForErrors()

    try {
        return block()
    }
    finally {
        glLog(ObjectUnbound, Buffer, "Unbinding buffer as $target")
        GL46C.glBindBuffer(target.glValue, 0)
        glCheckForErrors()
    }
}

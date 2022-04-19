package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLDebugger.LogAction.ObjectBound
import me.exerro.eggli.GLDebugger.LogAction.ObjectUnbound
import me.exerro.eggli.GLDebugger.LogEntity.Buffer
import me.exerro.eggli.enum.GLBufferTarget
import me.exerro.eggli.types.GLVertexArray
import org.lwjgl.opengl.GL46C

/** TODO
 *  https://www.khronos.org/opengl/wiki/GLAPI/glBindBuffer */
context (GLContext, GLDebugger.Context)
fun glBindBuffer(target: GLBufferTarget, vao: GLVertexArray) {
    GL46C.glBindBuffer(target.glValue, vao.get())
    glLog(ObjectBound, Buffer, "Bound buffer $vao as $target")
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun glBindBuffer(target: GLBufferTarget) {
    GL46C.glBindBuffer(target.glValue, 0)
    glLog(ObjectUnbound, Buffer, "Unbound buffer as $target")
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun <T> glBindBuffer(target: GLBufferTarget, vao: GLVertexArray, block: () -> T): T {
    GL46C.glBindBuffer(target.glValue, vao.get())
    glLog(ObjectBound, Buffer, "Bound buffer $vao as $target")
    glCheckForErrors()

    try {
        return block()
    }
    finally {
        GL46C.glBindBuffer(target.glValue, 0)
        glLog(ObjectUnbound, Buffer, "Unbound buffer as $target")
        glCheckForErrors()
    }
}

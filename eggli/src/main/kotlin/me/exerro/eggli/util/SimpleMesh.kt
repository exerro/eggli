package me.exerro.eggli.util

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLResource
import me.exerro.eggli.types.GLBuffer
import me.exerro.eggli.types.GLVertexArray
import me.exerro.eggli.types.GLAttributeIndex
import me.exerro.lifetimes.Lifetime

/** TODO */
data class SimpleMesh(
    val vertices: Int,
    val vertexArray: GLVertexArray,
    val dataBuffers: List<GLBuffer>,
    val elementBuffer: GLBuffer,
    val usesElementBuffer: Boolean,
) {
    /** @see SimpleMesh */
    companion object {
        /** Attribute index of the position vertex buffer within the VAO. */
        const val POSITION_ATTRIBUTE: GLAttributeIndex = 0

        /** Attribute index of the UV vertex buffer within the VAO. */
        const val UV_ATTRIBUTE: GLAttributeIndex = 1

        /** Attribute index of the normal vertex buffer within the VAO. */
        const val NORMAL_ATTRIBUTE: GLAttributeIndex = 2

        /** Attribute index of the normal vertex buffer within the VAO. */
        const val COLOUR_ATTRIBUTE: GLAttributeIndex = 3

        /** Size (number of components) of positions in vertices. */
        const val POSITION_COMPONENTS = 3

        /** Size (number of components) of UVs in vertices. */
        const val UV_COMPONENTS = 2

        /** Size (number of components) of normals in vertices. */
        const val NORMAL_COMPONENTS = 3

        /** Size (number of components) of normals in vertices. */
        const val COLOUR_COMPONENTS = 4

        /** TODO */
        context (GLContext, Lifetime, GLDebugger.Context)
        fun createGLResource(
            vertices: Int,
            vertexArray: GLVertexArray,
            dataBuffers: List<GLBuffer>,
            elementBuffer: GLBuffer,
            usesElementBuffer: Boolean,
        ) = GLResource(SimpleMesh(
            vertices = vertices,
            vertexArray = vertexArray,
            dataBuffers = dataBuffers,
            elementBuffer = elementBuffer,
            usesElementBuffer = usesElementBuffer,
        )) {
            it.vertexArray.destroy()
            it.dataBuffers.forEach { b -> b.destroy() }
            it.elementBuffer.destroy()
        }
    }
}

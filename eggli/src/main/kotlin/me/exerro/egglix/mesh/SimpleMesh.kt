package me.exerro.egglix.mesh

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLResource
import me.exerro.eggli.gl.glBindVertexArray
import me.exerro.eggli.gl.glDrawArrays
import me.exerro.eggli.gl.glDrawElements
import me.exerro.eggli.types.GLBuffer
import me.exerro.eggli.types.GLVertexArray
import me.exerro.eggli.types.GLAttributeIndex
import me.exerro.lifetimes.Lifetime

/**
 * A simple mesh comprised of a vertex count, vertex array, some number of
 * buffers, and an optional element buffer.
 */
data class SimpleMesh(
    val vertices: Int,
    val vertexArray: GLVertexArray,
    val dataBuffers: List<GLBuffer>,
    val elementBuffer: GLBuffer?,
) {
    /**
     * Draw the mesh by binding the VAO and calling `glDrawArrays` or
     * `glDrawElements` with the right vertex count. Which function is called
     * depends on whether the mesh has an element buffer.
     */
    context (GLContext)
    fun draw() {
        glBindVertexArray(vertexArray) {
            if (elementBuffer == null)
                glDrawArrays(count = vertices)
            else
                glDrawElements(count = vertices)
        }
    }

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

        /**
         * Wrap a new [SimpleMesh] in a GLResource so that when it is destroyed,
         * the component objects are also destroyed.
         */
        context (GLContext, Lifetime)
        fun createGLResource(
            vertices: Int,
            vertexArray: GLVertexArray,
            dataBuffers: List<GLBuffer>,
            elementBuffer: GLBuffer?,
        ) = GLResource(SimpleMesh(
            vertices = vertices,
            vertexArray = vertexArray,
            dataBuffers = dataBuffers,
            elementBuffer = elementBuffer,
        )) {
            it.vertexArray.destroy()
            it.dataBuffers.forEach { b -> b.destroy() }
            it.elementBuffer?.destroy()
        }
    }
}

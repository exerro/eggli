package me.exerro.eggli.util

import me.exerro.eggli.GL
import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLResource
import me.exerro.eggli.enum.GLBufferTarget
import me.exerro.eggli.enum.GLType
import me.exerro.eggli.gl.*
import me.exerro.eggli.types.GLBuffer
import me.exerro.eggli.types.GLVertexArray
import me.exerro.eggli.util.DefaultCubeObjects.Companion.COLOUR_VERTEX_BUFFER_INDEX
import me.exerro.eggli.util.DefaultCubeObjects.Companion.NORMAL_VERTEX_BUFFER_INDEX
import me.exerro.eggli.util.DefaultCubeObjects.Companion.POSITION_VERTEX_BUFFER_INDEX
import me.exerro.eggli.util.DefaultCubeObjects.Companion.UV_VERTEX_BUFFER_INDEX
import me.exerro.eggli.util.DefaultCubeObjects.Companion.COLOUR_VERTEX_BUFFER_COMPONENTS
import me.exerro.eggli.util.DefaultCubeObjects.Companion.NORMAL_VERTEX_BUFFER_COMPONENTS
import me.exerro.eggli.util.DefaultCubeObjects.Companion.POSITION_VERTEX_BUFFER_COMPONENTS
import me.exerro.eggli.util.DefaultCubeObjects.Companion.UV_VERTEX_BUFFER_COMPONENTS
import me.exerro.lifetimes.Lifetime

/** TODO */
data class DefaultFaceObjects(
    val vertexArray: GLVertexArray,
    val positionBuffer: GLBuffer,
    val uvBuffer: GLBuffer,
    val normalBuffer: GLBuffer,
    val colourBuffer: GLBuffer,
    val elementBuffer: GLBuffer,
) {
    /** @see DefaultFaceObjects */
    companion object {
        /** Index of the position vertex buffer within the vertex array. */
        const val POSITION_VERTEX_BUFFER_INDEX = 0

        /** Index of the UV vertex buffer within the vertex array. */
        const val UV_VERTEX_BUFFER_INDEX = 1

        /** Index of the normal vertex buffer within the vertex array. */
        const val NORMAL_VERTEX_BUFFER_INDEX = 2

        /** Index of the normal vertex buffer within the vertex array. */
        const val COLOUR_VERTEX_BUFFER_INDEX = 3

        /** Size (number of components) of the position vertex buffer. */
        const val POSITION_VERTEX_BUFFER_COMPONENTS = 3

        /** Size (number of components) of the UV vertex buffer. */
        const val UV_VERTEX_BUFFER_COMPONENTS = 2

        /** Size (number of components) of the normal vertex buffer. */
        const val NORMAL_VERTEX_BUFFER_COMPONENTS = 3

        /** Size (number of components) of the normal vertex buffer. */
        const val COLOUR_VERTEX_BUFFER_COMPONENTS = 4

        /** TODO */
        const val VERTICES = 6
    }
}

/** TODO */
context (Lifetime, GLDebugger.Context)
fun createDefaultFace(
    includeUVs: Boolean = true,
    includeNormals: Boolean = true,
    includeColours: Boolean = false,
    useElements: Boolean = false,
    width: Float = 1f,
    height: Float = width,
    depth: Float = width,
    centreX: Float = 0f,
    centreY: Float = 0f,
    centreZ: Float = -1f,
) = GL {
    // TODO: look into creating a single buffer!

    val (vertexArray) = glGenVertexArrays()
    val (positionBuffer) = glCreateBuffers()
    val (uvBuffer) = createBuffer(includeUVs)
    val (normalBuffer) = createBuffer(includeNormals)
    val (colourBuffer) = createBuffer(includeColours)
    val (elementBuffer) = createBuffer(useElements)

    val positionData = if (useElements) FACE_POSITIONS.copyOf(FACE_POSITIONS.size) else genFaceData(POSITION_VERTEX_BUFFER_COMPONENTS, FACE_POSITIONS, Face_ELEMENTS)
    val uvData = if (!includeUVs || useElements) FACE_UVS else genFaceData(UV_VERTEX_BUFFER_COMPONENTS, FACE_UVS, Face_ELEMENTS)
    val normalData = if (!includeNormals || useElements) FACE_NORMALS else genFaceData(NORMAL_VERTEX_BUFFER_COMPONENTS, FACE_NORMALS, Face_ELEMENTS)
    val colourData = if (!includeColours || useElements) FACE_COLOURS else genFaceData(COLOUR_VERTEX_BUFFER_COMPONENTS, FACE_COLOURS, Face_ELEMENTS)

    for (i in positionData.indices step 3) {
        positionData[i] *= width
        positionData[i] += centreX
        positionData[i + 1] *= height
        positionData[i + 1] += centreY
        positionData[i + 2] *= depth
        positionData[i + 2] += centreZ
    }

    glNamedBufferData(positionBuffer, positionData)
    if (includeUVs) glNamedBufferData(uvBuffer, uvData)
    if (includeNormals) glNamedBufferData(normalBuffer, normalData)
    if (includeColours) glNamedBufferData(colourBuffer, colourData)
    if (useElements) glNamedBufferData(elementBuffer, Face_ELEMENTS)

    glBindVertexArray(vertexArray) {
        glBindBuffer(GLBufferTarget.ArrayBuffer, positionBuffer) {
            glVertexAttribPointer(POSITION_VERTEX_BUFFER_INDEX, POSITION_VERTEX_BUFFER_COMPONENTS, GLType.Float)
        }

        if (includeUVs) glBindBuffer(GLBufferTarget.ArrayBuffer, uvBuffer) {
            glVertexAttribPointer(UV_VERTEX_BUFFER_INDEX, UV_VERTEX_BUFFER_COMPONENTS, GLType.Float)
        }

        if (includeNormals) glBindBuffer(GLBufferTarget.ArrayBuffer, normalBuffer) {
            glVertexAttribPointer(NORMAL_VERTEX_BUFFER_INDEX, NORMAL_VERTEX_BUFFER_COMPONENTS, GLType.Float)
        }

        if (includeColours) glBindBuffer(GLBufferTarget.ArrayBuffer, colourBuffer) {
            glVertexAttribPointer(COLOUR_VERTEX_BUFFER_INDEX, COLOUR_VERTEX_BUFFER_COMPONENTS, GLType.Float)
        }

        if (useElements) {
            glBindBuffer(GLBufferTarget.ElementArrayBuffer, elementBuffer)
        }
    }

    glEnableVertexAttribArray(vertexArray, POSITION_VERTEX_BUFFER_INDEX)
    if (includeUVs) glEnableVertexAttribArray(vertexArray, UV_VERTEX_BUFFER_INDEX)
    if (includeNormals) glEnableVertexAttribArray(vertexArray, NORMAL_VERTEX_BUFFER_INDEX)
    if (includeColours) glEnableVertexAttribArray(vertexArray, COLOUR_VERTEX_BUFFER_INDEX)

    val cubeObjects = DefaultCubeObjects(
        vertexArray = vertexArray,
        positionBuffer = positionBuffer,
        uvBuffer = uvBuffer,
        normalBuffer = normalBuffer,
        colourBuffer = colourBuffer,
        elementBuffer = elementBuffer,
    )

    GLResource(cubeObjects) {
        it.vertexArray.destroy()
        it.positionBuffer.destroy()
        it.uvBuffer.destroy()
        it.normalBuffer.destroy()
        it.colourBuffer.destroy()
        it.elementBuffer.destroy()
    }
}

context (GLContext, Lifetime, GLDebugger.Context)
private fun createBuffer(include: Boolean): GL<GLBuffer> =
    when (include) {
        true -> glCreateBuffers()
        else -> GL { GLResource.createDestroyed() }
    }

private fun genFaceData(size: Int, data: FloatArray, elements: IntArray) =
    FloatArray(size * elements.size) { index ->
        data[elements[index / size] * size + index % size]
    }

private val FACE_POSITIONS = floatArrayOf(
    -0.5f,  0.5f,  0.5f,
    -0.5f, -0.5f,  0.5f,
     0.5f, -0.5f,  0.5f,
     0.5f,  0.5f,  0.5f,
)

private val FACE_UVS = floatArrayOf(
    0f, 0f,
    0f, 1f,
    1f, 1f,
    1f, 0f,
)

private val FACE_NORMALS = floatArrayOf(
     0f,  0f,  1f,
     0f,  0f,  1f,
     0f,  0f,  1f,
     0f,  0f,  1f,
)

private val FACE_COLOURS = floatArrayOf(
    0f, 0f, 1f, 1f,
    0f, 0f, 1f, 1f,
    0f, 0f, 1f, 1f,
    0f, 0f, 1f, 1f,
)

private val Face_ELEMENTS = intArrayOf(
     0,  1,  2,  0,  2,  3,
)

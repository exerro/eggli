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
import me.exerro.eggli.util.DefaultCubeObjects.Companion.POSITION_VERTEX_ARRAY_INDEX
import me.exerro.eggli.util.DefaultCubeObjects.Companion.UV_VERTEX_ARRAY_INDEX
import me.exerro.eggli.util.DefaultCubeObjects.Companion.NORMAL_VERTEX_ARRAY_INDEX
import me.exerro.eggli.util.DefaultCubeObjects.Companion.COLOUR_VERTEX_ARRAY_INDEX
import me.exerro.lifetimes.Lifetime

/** TODO */
data class DefaultCubeObjects(
    val vertexArray: GLVertexArray,
    val positionBuffer: GLBuffer,
    val uvBuffer: GLBuffer,
    val normalBuffer: GLBuffer,
    val colourBuffer: GLBuffer,
    val elementBuffer: GLBuffer,
) {
    /** @see DefaultCubeObjects */
    companion object {
        /** TODO */
        const val POSITION_VERTEX_ARRAY_INDEX = 0

        /** TODO */
        const val UV_VERTEX_ARRAY_INDEX = 1

        /** TODO */
        const val NORMAL_VERTEX_ARRAY_INDEX = 2

        /** TODO */
        const val COLOUR_VERTEX_ARRAY_INDEX = 3

        /** TODO */
        const val VERTICES = 36
    }
}

/** TODO */
context (Lifetime, GLDebugger.Context)
fun createDefaultCube(
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
    val (vertexArray) = glGenVertexArrays()
    val (positionBuffer) = glGenBuffers()
    val (uvBuffer) = createBuffer(includeUVs)
    val (normalBuffer) = createBuffer(includeNormals)
    val (colourBuffer) = createBuffer(includeColours)
    val (elementBuffer) = createBuffer(useElements)

    val positionData = if (useElements) CUBE_POSITIONS else genCubeData(3, CUBE_POSITIONS, CUBE_ELEMENTS)
    val uvData = if (!includeUVs || useElements) CUBE_UVS else genCubeData(2, CUBE_UVS, CUBE_ELEMENTS)
    val normalData = if (!includeNormals || useElements) CUBE_NORMALS else genCubeData(3, CUBE_NORMALS, CUBE_ELEMENTS)
    val colourData = if (!includeColours || useElements) CUBE_COLOURS else genCubeData(4, CUBE_COLOURS, CUBE_ELEMENTS)

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
    if (useElements) glNamedBufferData(elementBuffer, CUBE_ELEMENTS)

    glBindVertexArray(vertexArray) {
        glBindBuffer(GLBufferTarget.Array, positionBuffer) {
            glVertexAttribPointer(POSITION_VERTEX_ARRAY_INDEX, 3, GLType.Float)
        }

        if (includeUVs) glBindBuffer(GLBufferTarget.Array, uvBuffer) {
            glVertexAttribPointer(UV_VERTEX_ARRAY_INDEX, 2, GLType.Float)
        }

        if (includeNormals) glBindBuffer(GLBufferTarget.Array, normalBuffer) {
            glVertexAttribPointer(NORMAL_VERTEX_ARRAY_INDEX, 3, GLType.Float)
        }

        if (includeColours) glBindBuffer(GLBufferTarget.Array, colourBuffer) {
            glVertexAttribPointer(COLOUR_VERTEX_ARRAY_INDEX, 4, GLType.Float)
        }

        if (useElements) {
            glBindBuffer(GLBufferTarget.ElementArray, elementBuffer)
        }
    }

    glEnableVertexAttribArray(vertexArray, POSITION_VERTEX_ARRAY_INDEX)
    if (includeUVs) glEnableVertexAttribArray(vertexArray, UV_VERTEX_ARRAY_INDEX)
    if (includeNormals) glEnableVertexAttribArray(vertexArray, NORMAL_VERTEX_ARRAY_INDEX)
    if (includeColours) glEnableVertexAttribArray(vertexArray, COLOUR_VERTEX_ARRAY_INDEX)

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
        true -> glGenBuffers()
        else -> GL { GLResource.createDestroyed() }
    }

private fun genCubeData(size: Int, data: FloatArray, elements: IntArray) =
    FloatArray(size * elements.size) { index ->
        data[elements[index / size] + index % size]
    }

private val CUBE_POSITIONS = floatArrayOf(
    // front
    -0.5f,  0.5f,  0.5f,
    -0.5f, -0.5f,  0.5f,
     0.5f, -0.5f,  0.5f,
     0.5f,  0.5f,  0.5f,
    // back
     0.5f,  0.5f, -0.5f,
     0.5f, -0.5f, -0.5f,
    -0.5f, -0.5f, -0.5f,
    -0.5f,  0.5f, -0.5f,
    // left
    -0.5f,  0.5f, -0.5f,
    -0.5f, -0.5f, -0.5f,
    -0.5f, -0.5f,  0.5f,
    -0.5f,  0.5f,  0.5f,
    // right
     0.5f,  0.5f,  0.5f,
     0.5f, -0.5f,  0.5f,
     0.5f, -0.5f, -0.5f,
     0.5f,  0.5f, -0.5f,
    // top
    -0.5f,  0.5f, -0.5f,
    -0.5f,  0.5f,  0.5f,
     0.5f,  0.5f,  0.5f,
     0.5f,  0.5f, -0.5f,
    // bottom
    -0.5f, -0.5f,  0.5f,
    -0.5f, -0.5f, -0.5f,
     0.5f, -0.5f, -0.5f,
     0.5f, -0.5f,  0.5f,
)

private val CUBE_UVS = floatArrayOf(
    // front
    0.25f, 0.3333333f,
    0.25f, 0.6666667f,
    0.50f, 0.6666667f,
    0.50f, 0.3333333f,
    // back
    0.75f, 0.3333333f,
    0.75f, 0.6666667f,
    1.00f, 0.6666667f,
    1.00f, 0.3333333f,
    // left
    0.00f, 0.3333333f,
    0.00f, 0.6666667f,
    0.25f, 0.6666667f,
    0.25f, 0.3333333f,
    // right
    0.50f, 0.3333333f,
    0.50f, 0.6666667f,
    0.75f, 0.6666667f,
    0.75f, 0.3333333f,
    // top
    0.25f, 0.00f,
    0.25f, 0.3333333f,
    0.50f, 0.3333333f,
    0.50f, 0.00f,
    // bottom
    0.25f, 0.6666667f,
    0.25f, 1.00f,
    0.50f, 1.00f,
    0.50f, 0.6666667f,
)

private val CUBE_NORMALS = floatArrayOf(
    // front
     0f,  0f,  1f,
     0f,  0f,  1f,
     0f,  0f,  1f,
     0f,  0f,  1f,
    // back
     0f,  0f, -1f,
     0f,  0f, -1f,
     0f,  0f, -1f,
     0f,  0f, -1f,
    // left
    -1f,  0f,  0f,
    -1f,  0f,  0f,
    -1f,  0f,  0f,
    -1f,  0f,  0f,
    // right
     1f,  0f,  0f,
     1f,  0f,  0f,
     1f,  0f,  0f,
     1f,  0f,  0f,
    // top
     0f,  1f,  0f,
     0f,  1f,  0f,
     0f,  1f,  0f,
     0f,  1f,  0f,
    // bottom
     0f, -1f,  0f,
     0f, -1f,  0f,
     0f, -1f,  0f,
     0f, -1f,  0f,
)

private val CUBE_COLOURS = floatArrayOf(
    // front
    0f, 0f, 1f, 1f,
    0f, 0f, 1f, 1f,
    0f, 0f, 1f, 1f,
    0f, 0f, 1f, 1f,
    // back
    1f, 1f, 0f, 1f,
    1f, 1f, 0f, 1f,
    1f, 1f, 0f, 1f,
    1f, 1f, 0f, 1f,
    // left
    0f, 1f, 1f, 1f,
    0f, 1f, 1f, 1f,
    0f, 1f, 1f, 1f,
    0f, 1f, 1f, 1f,
    // right
    1f, 0f, 0f, 1f,
    1f, 0f, 0f, 1f,
    1f, 0f, 0f, 1f,
    1f, 0f, 0f, 1f,
    // top
    0f, 1f, 0f, 1f,
    0f, 1f, 0f, 1f,
    0f, 1f, 0f, 1f,
    0f, 1f, 0f, 1f,
    // bottom
    1f, 0f, 1f, 1f,
    1f, 0f, 1f, 1f,
    1f, 0f, 1f, 1f,
    1f, 0f, 1f, 1f,
)

private val CUBE_ELEMENTS = intArrayOf(
     0,  1,  2,  0,  2,  3,
     4,  5,  6,  4,  6,  7,
     8,  9, 10,  8, 10, 11,
    12, 13, 14, 12, 14, 15,
    16, 17, 18, 16, 18, 19,
    20, 21, 22, 20, 22, 23,
)

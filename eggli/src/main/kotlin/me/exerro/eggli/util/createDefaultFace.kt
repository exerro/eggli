package me.exerro.eggli.util

import me.exerro.eggli.GL
import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.GLResource
import me.exerro.eggli.enum.GLBufferTarget
import me.exerro.eggli.enum.GLType
import me.exerro.eggli.gl.*
import me.exerro.eggli.types.GLBuffer
import me.exerro.eggli.util.SimpleMesh.Companion.COLOUR_ATTRIBUTE
import me.exerro.eggli.util.SimpleMesh.Companion.COLOUR_COMPONENTS
import me.exerro.eggli.util.SimpleMesh.Companion.NORMAL_ATTRIBUTE
import me.exerro.eggli.util.SimpleMesh.Companion.NORMAL_COMPONENTS
import me.exerro.eggli.util.SimpleMesh.Companion.POSITION_ATTRIBUTE
import me.exerro.eggli.util.SimpleMesh.Companion.POSITION_COMPONENTS
import me.exerro.eggli.util.SimpleMesh.Companion.UV_ATTRIBUTE
import me.exerro.eggli.util.SimpleMesh.Companion.UV_COMPONENTS
import me.exerro.lifetimes.Lifetime

/** TODO */
context (Lifetime, GLDebugger.Context)
fun createDefaultFace(
    includeUVs: Boolean = true,
    includeNormals: Boolean = true,
    includeColours: Boolean = false,
    useElements: Boolean = false,
    width: Float = 1f,
    height: Float = 1f,
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

    val positionData = if (useElements) FACE_POSITIONS.copyOf(FACE_POSITIONS.size) else genFaceData(POSITION_COMPONENTS, FACE_POSITIONS, FACE_ELEMENTS)
    val uvData = if (!includeUVs || useElements) FACE_UVS else genFaceData(UV_COMPONENTS, FACE_UVS, FACE_ELEMENTS)
    val normalData = if (!includeNormals || useElements) FACE_NORMALS else genFaceData(NORMAL_COMPONENTS, FACE_NORMALS, FACE_ELEMENTS)
    val colourData = if (!includeColours || useElements) FACE_COLOURS else genFaceData(COLOUR_COMPONENTS, FACE_COLOURS, FACE_ELEMENTS)

    for (i in positionData.indices step 3) {
        positionData[i] *= width
        positionData[i] += centreX
        positionData[i + 1] *= height
        positionData[i + 1] += centreY
        positionData[i + 2] += centreZ
    }

    glNamedBufferData(positionBuffer, positionData)
    if (includeUVs) glNamedBufferData(uvBuffer, uvData)
    if (includeNormals) glNamedBufferData(normalBuffer, normalData)
    if (includeColours) glNamedBufferData(colourBuffer, colourData)
    if (useElements) glNamedBufferData(elementBuffer, FACE_ELEMENTS)

    glBindVertexArray(vertexArray) {
        glBindBuffer(GLBufferTarget.ArrayBuffer, positionBuffer) {
            glVertexAttribPointer(POSITION_ATTRIBUTE, POSITION_COMPONENTS, GLType.Float)
        }

        if (includeUVs) glBindBuffer(GLBufferTarget.ArrayBuffer, uvBuffer) {
            glVertexAttribPointer(UV_ATTRIBUTE, UV_COMPONENTS, GLType.Float)
        }

        if (includeNormals) glBindBuffer(GLBufferTarget.ArrayBuffer, normalBuffer) {
            glVertexAttribPointer(NORMAL_ATTRIBUTE, NORMAL_COMPONENTS, GLType.Float)
        }

        if (includeColours) glBindBuffer(GLBufferTarget.ArrayBuffer, colourBuffer) {
            glVertexAttribPointer(COLOUR_ATTRIBUTE, COLOUR_COMPONENTS, GLType.Float)
        }

        if (useElements) {
            glBindBuffer(GLBufferTarget.ElementArrayBuffer, elementBuffer)
        }
    }

    glEnableVertexAttribArray(vertexArray, POSITION_ATTRIBUTE)
    if (includeUVs) glEnableVertexAttribArray(vertexArray, UV_ATTRIBUTE)
    if (includeNormals) glEnableVertexAttribArray(vertexArray, NORMAL_ATTRIBUTE)
    if (includeColours) glEnableVertexAttribArray(vertexArray, COLOUR_ATTRIBUTE)

    SimpleMesh.createGLResource(
        vertices = FACE_ELEMENTS.size,
        vertexArray = vertexArray,
        dataBuffers = listOf(positionBuffer, uvBuffer, normalBuffer, colourBuffer),
        elementBuffer = elementBuffer,
        usesElementBuffer = useElements,
    )
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
    1f, 1f, 0f, 1f,
    0f, 0f, 1f, 1f,
    0f, 1f, 0f, 1f,
    1f, 0f, 0f, 1f,
)

private val FACE_ELEMENTS = intArrayOf(
     0,  1,  2,  0,  2,  3,
)

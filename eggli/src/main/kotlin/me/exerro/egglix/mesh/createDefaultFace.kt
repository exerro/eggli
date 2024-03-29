package me.exerro.egglix.mesh

import me.exerro.eggli.GL
import me.exerro.eggli.GLContext
import me.exerro.eggli.GLResource
import me.exerro.eggli.enum.*
import me.exerro.eggli.gl.*
import me.exerro.eggli.types.GLBuffer
import me.exerro.egglix.mesh.SimpleMesh.Companion.COLOUR_ATTRIBUTE
import me.exerro.egglix.mesh.SimpleMesh.Companion.COLOUR_COMPONENTS
import me.exerro.egglix.mesh.SimpleMesh.Companion.NORMAL_ATTRIBUTE
import me.exerro.egglix.mesh.SimpleMesh.Companion.NORMAL_COMPONENTS
import me.exerro.egglix.mesh.SimpleMesh.Companion.POSITION_ATTRIBUTE
import me.exerro.egglix.mesh.SimpleMesh.Companion.POSITION_COMPONENTS
import me.exerro.egglix.mesh.SimpleMesh.Companion.UV_ATTRIBUTE
import me.exerro.egglix.mesh.SimpleMesh.Companion.UV_COMPONENTS
import me.exerro.lifetimes.Lifetime

/**
 * Create a [SimpleMesh] resource representing a flat forward-facing (+Z) square.
 *
 * Note: using [useElements] will cause included normals and colours to be a bit
 *       weird.
 *
 * @param includeUVs include UV coordinates for each vertex in the cube.
 * @param includeNormals generate normal vectors for each vertex (face normals.)
 * @param includeColours include a colour component for each vertex which gives
 *                       each face a unique colour.
 * @param useElements use 8 indexed vertices instead of 24 non-indexed vertices
 *                    by using an element array buffer. See note below!
 * @param width width of the cube (distance right-left X), defaults to 1
 * @param height height of the cube (distance top-bottom Y), defaults to 1
 * @param centreX centre X value of the cube, defaults to 0
 * @param centreY centre Y value of the cube, defaults to 0
 * @param centreZ centre Z value of the cube, defaults to -1
 */
context (Lifetime)
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
    // TODO: use a single buffer!

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
    if (includeUVs) glNamedBufferData(uvBuffer!!, uvData)
    if (includeNormals) glNamedBufferData(normalBuffer!!, normalData)
    if (includeColours) glNamedBufferData(colourBuffer!!, colourData)
    if (useElements) glNamedBufferData(elementBuffer!!, FACE_ELEMENTS)

    glBindVertexArray(vertexArray) {
        glBindBuffer(GL_ARRAY_BUFFER, positionBuffer) {
            glVertexAttribPointer(POSITION_ATTRIBUTE, POSITION_COMPONENTS, GL_FLOAT)
        }

        if (includeUVs) glBindBuffer(GL_ARRAY_BUFFER, uvBuffer!!) {
            glVertexAttribPointer(UV_ATTRIBUTE, UV_COMPONENTS, GL_FLOAT)
        }

        if (includeNormals) glBindBuffer(GL_ARRAY_BUFFER, normalBuffer!!) {
            glVertexAttribPointer(NORMAL_ATTRIBUTE, NORMAL_COMPONENTS, GL_FLOAT)
        }

        if (includeColours) glBindBuffer(GL_ARRAY_BUFFER, colourBuffer!!) {
            glVertexAttribPointer(COLOUR_ATTRIBUTE, COLOUR_COMPONENTS, GL_FLOAT)
        }

        if (useElements) {
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, elementBuffer!!)
        }
    }

    glEnableVertexAttribArray(vertexArray, POSITION_ATTRIBUTE)
    if (includeUVs) glEnableVertexAttribArray(vertexArray, UV_ATTRIBUTE)
    if (includeNormals) glEnableVertexAttribArray(vertexArray, NORMAL_ATTRIBUTE)
    if (includeColours) glEnableVertexAttribArray(vertexArray, COLOUR_ATTRIBUTE)

    SimpleMesh.createGLResource(
        vertices = FACE_ELEMENTS.size,
        vertexArray = vertexArray,
        dataBuffers = listOfNotNull(positionBuffer, uvBuffer, normalBuffer, colourBuffer),
        elementBuffer = elementBuffer,
    )
}

context (GLContext, Lifetime)
private fun createBuffer(include: Boolean): GL<GLBuffer?> =
    when (include) {
        true -> glCreateBuffers()
        else -> GL { null }
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

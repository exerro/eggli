package me.exerro.egglix.mesh

import me.exerro.eggli.GL
import me.exerro.eggli.GLContext
import me.exerro.eggli.GLResource
import me.exerro.eggli.enum.GL_ARRAY_BUFFER
import me.exerro.eggli.enum.GL_ELEMENT_ARRAY_BUFFER
import me.exerro.eggli.enum.GL_FLOAT
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
 * Create a [SimpleMesh] resource representing a cube.
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
 * @param depth depth of the cube (distance front-back Z), defaults to 1
 * @param centreX centre X value of the cube, defaults to 0
 * @param centreY centre Y value of the cube, defaults to 0
 * @param centreZ centre Z value of the cube, defaults to -1
 * @param uvsPerFace if including UV coordinates, whether each face should get a
 *                   unique set of UVs like a cross in a texture instead of each
 *                   face getting the same 0-1 UV range.
 */
context (Lifetime)
fun createDefaultCube(
    includeUVs: Boolean = true,
    includeNormals: Boolean = true,
    includeColours: Boolean = false,
    useElements: Boolean = false,
    width: Float = 1f,
    height: Float = 1f,
    depth: Float = 1f,
    centreX: Float = 0f,
    centreY: Float = 0f,
    centreZ: Float = -1f,
    uvsPerFace: Boolean = true,
): GL<GLResource<SimpleMesh>> = GL {
    // TODO: use a single buffer!

    val (vertexArray) = glGenVertexArrays()
    val (positionBuffer) = glCreateBuffers()
    val (uvBuffer) = createBuffer(includeUVs)
    val (normalBuffer) = createBuffer(includeNormals)
    val (colourBuffer) = createBuffer(includeColours)
    val (elementBuffer) = createBuffer(useElements)

    val uvSourceData = if (uvsPerFace) CUBE_UVS_PER_FACE else CUBE_UVS
    val positionData = if (useElements) CUBE_POSITIONS.copyOf(CUBE_POSITIONS.size) else genCubeData(POSITION_COMPONENTS, CUBE_POSITIONS, CUBE_ELEMENTS)
    val uvData = if (!includeUVs || useElements) uvSourceData else genCubeData(UV_COMPONENTS, uvSourceData, CUBE_ELEMENTS)
    val normalData = if (!includeNormals || useElements) CUBE_NORMALS else genCubeData(NORMAL_COMPONENTS, CUBE_NORMALS, CUBE_ELEMENTS)
    val colourData = if (!includeColours || useElements) CUBE_COLOURS else genCubeData(COLOUR_COMPONENTS, CUBE_COLOURS, CUBE_ELEMENTS)

    for (i in positionData.indices step 3) {
        positionData[i] *= width
        positionData[i] += centreX
        positionData[i + 1] *= height
        positionData[i + 1] += centreY
        positionData[i + 2] *= depth
        positionData[i + 2] += centreZ
    }

    glNamedBufferData(positionBuffer, positionData)
    if (includeUVs) glNamedBufferData(uvBuffer!!, uvData)
    if (includeNormals) glNamedBufferData(normalBuffer!!, normalData)
    if (includeColours) glNamedBufferData(colourBuffer!!, colourData)
    if (useElements) glNamedBufferData(elementBuffer!!, CUBE_ELEMENTS)

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
        vertices = CUBE_ELEMENTS.size,
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

private fun genCubeData(size: Int, data: FloatArray, elements: IntArray) =
    FloatArray(size * elements.size) { index ->
        data[elements[index / size] * size + index % size]
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

private val CUBE_UVS_PER_FACE = floatArrayOf(
    0f, 0f, 0f, 1f, 1f, 1f, 1f, 0f,
    0f, 0f, 0f, 1f, 1f, 1f, 1f, 0f,
    0f, 0f, 0f, 1f, 1f, 1f, 1f, 0f,
    0f, 0f, 0f, 1f, 1f, 1f, 1f, 0f,
    0f, 0f, 0f, 1f, 1f, 1f, 1f, 0f,
    0f, 0f, 0f, 1f, 1f, 1f, 1f, 0f,
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

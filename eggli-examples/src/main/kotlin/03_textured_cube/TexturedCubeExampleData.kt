package `03_textured_cube`

import me.exerro.eggli.GLResource
import me.exerro.eggli.types.GLShaderProgram
import me.exerro.eggli.types.GLTexture
import me.exerro.eggli.types.GLUniformLocation
import me.exerro.eggli.util.SimpleMesh

data class TexturedCubeExampleData(
    val shaderProgram: GLShaderProgram,
    val mesh: GLResource<SimpleMesh>,
    val texture: GLTexture,
    val timeUniformLocation: GLUniformLocation,
)

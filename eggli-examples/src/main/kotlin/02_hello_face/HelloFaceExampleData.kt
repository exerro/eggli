package `02_hello_face`

import me.exerro.eggli.GLResource
import me.exerro.eggli.types.GLShaderProgram
import me.exerro.eggli.types.GLUniformLocation
import me.exerro.egglix.mesh.SimpleMesh

/** Store the data we use for rendering. */
data class HelloFaceExampleData(
    val shaderProgram: GLShaderProgram,
    val mesh: GLResource<SimpleMesh>,
    val timeUniformLocation: GLUniformLocation,
)

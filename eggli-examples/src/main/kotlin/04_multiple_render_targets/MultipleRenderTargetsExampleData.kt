package `04_multiple_render_targets`

import me.exerro.eggli.GLResource
import me.exerro.eggli.types.GLFramebuffer
import me.exerro.eggli.types.GLShaderProgram
import me.exerro.eggli.types.GLTexture
import me.exerro.eggli.types.GLUniformLocation
import me.exerro.egglix.mesh.SimpleMesh

data class MultipleRenderTargetsExampleData(
    val modelShaderProgram: GLShaderProgram,
    val screenShaderProgram: GLShaderProgram,
    val modelMatrixUniformLocation: GLUniformLocation,
    val modelMesh: GLResource<SimpleMesh>,
    val topLeftQuad: GLResource<SimpleMesh>,
    val topRightQuad: GLResource<SimpleMesh>,
    val bottomLeftQuad: GLResource<SimpleMesh>,
    val bottomRightQuad: GLResource<SimpleMesh>,
    val framebuffer: GLFramebuffer,
    val modelTexture: GLTexture,
    val albedoTexture: GLTexture,
    val positionTexture: GLTexture,
    val normalTexture: GLTexture,
    val depthTexture: GLTexture,
)

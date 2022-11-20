package `06_ssao`

import me.exerro.eggli.GLResource
import me.exerro.eggli.types.GLFramebuffer
import me.exerro.eggli.types.GLShaderProgram
import me.exerro.eggli.types.GLTexture
import me.exerro.egglix.mesh.SimpleMesh
import me.exerro.egglix.postprocessing.FullscreenQuad
import me.exerro.egglix.postprocessing.SSAO

data class SSAOExampleData(
    val modelShaderProgram: GLShaderProgram,
    val framebuffer: GLFramebuffer,
    val albedoTexture: GLTexture,
    val positionTexture: GLTexture,
    val normalTexture: GLTexture,
    val ssao: SSAO,
    val quad: FullscreenQuad,
    val objects: List<GLResource<SimpleMesh>>,
)

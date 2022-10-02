package `05_fxaa`

import me.exerro.eggli.GLResource
import me.exerro.eggli.types.GLFramebuffer
import me.exerro.eggli.types.GLShaderProgram
import me.exerro.eggli.types.GLTexture
import me.exerro.eggli.types.GLUniformLocation
import me.exerro.egglix.mesh.SimpleMesh
import me.exerro.egglix.postprocessing.FXAA
import me.exerro.egglix.postprocessing.FullscreenQuad

data class FXAAExampleData(
    val modelShaderProgram: GLShaderProgram,
    val framebuffer: GLFramebuffer,
    val albedoTexture: GLTexture,
    val fxaa: FXAA,
    val quad: FullscreenQuad,
    val objects: List<GLResource<SimpleMesh>>,
)

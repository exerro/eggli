package me.exerro.egglix.shader

import me.exerro.eggli.types.GLAttributeIndex
import me.exerro.egglix.mesh.SimpleMesh

/** TODO */
data class ShaderAttribute(
    val name: String,
    val type: String,
    val index: GLAttributeIndex,
) {
    /** @see ShaderAttribute */
    companion object {
        /** TODO */
        val position = ShaderAttribute("position", "vec${SimpleMesh.POSITION_COMPONENTS}", SimpleMesh.POSITION_ATTRIBUTE)

        /** TODO */
        val uv = ShaderAttribute("uv", "vec${SimpleMesh.UV_COMPONENTS}", SimpleMesh.UV_ATTRIBUTE)

        /** TODO */
        val normal = ShaderAttribute("normal", "vec${SimpleMesh.NORMAL_COMPONENTS}", SimpleMesh.NORMAL_ATTRIBUTE)

        /** TODO */
        val colour = ShaderAttribute("colour", "vec${SimpleMesh.COLOUR_COMPONENTS}", SimpleMesh.COLOUR_ATTRIBUTE)
    }
}

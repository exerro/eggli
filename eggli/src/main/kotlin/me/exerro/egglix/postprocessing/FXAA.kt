package me.exerro.egglix.postprocessing

import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.GLActiveTexture
import me.exerro.eggli.enum.GL_TEXTURE0
import me.exerro.eggli.gl.glGetUniformLocation
import me.exerro.eggli.gl.glProgramUniform1f
import me.exerro.eggli.gl.glProgramUniform1i
import me.exerro.eggli.gl.glProgramUniform2f
import me.exerro.eggli.types.GLShaderProgram
import me.exerro.eggli.types.GLUniformLocation
import me.exerro.egglix.shader.createShaderProgram
import me.exerro.lifetimes.Lifetime

/** TODO */
class FXAA(
    val shader: GLShaderProgram,
    val viewportSizeUniform: GLUniformLocation,
    val textureUniform: GLUniformLocation,
    val showEdgesUniform: GLUniformLocation,
    val fxaaOnUniform: GLUniformLocation,
    val lumaThresholdUniform: GLUniformLocation,
    val mulReduceUniform: GLUniformLocation,
    val minReduceUniform: GLUniformLocation,
    val maxSpanUniform: GLUniformLocation,
) {
    /** TODO */
    context (GLContext)
    fun setViewportSize(width: Float, height: Float) {
        glProgramUniform2f(shader, viewportSizeUniform, width, height)
    }

    /** TODO */
    context (GLContext)
    fun setViewportSize(width: Int, height: Int) =
        setViewportSize(width.toFloat(), height.toFloat())

    /** TODO */
    context (GLContext)
    fun setTexture(binding: GLActiveTexture) {
        glProgramUniform1i(shader, textureUniform, binding.glValue)
    }

    /** TODO */
    context (GLContext)
    fun setShowEdges(showEdges: Boolean) {
        glProgramUniform1i(shader, showEdgesUniform, showEdges)
    }

    /** TODO */
    context (GLContext)
    fun setEnabled(enabled: Boolean) {
        glProgramUniform1i(shader, fxaaOnUniform, enabled)
    }

    /** TODO */
    context (GLContext)
    fun setLumaThreshold(lumaThreshold: Float) {
        glProgramUniform1f(shader, lumaThresholdUniform, lumaThreshold)
    }

    /** TODO */
    context (GLContext)
    fun setMulReduce(mulReduce: Float) {
        glProgramUniform1f(shader, mulReduceUniform, mulReduce)
    }

    /** TODO */
    context (GLContext)
    fun setMinReduce(minReduce: Float) {
        glProgramUniform1f(shader, minReduceUniform, minReduce)
    }

    /** TODO */
    context (GLContext)
    fun setMaxSpan(maxSpan: Float) {
        glProgramUniform1f(shader, maxSpanUniform, maxSpan)
    }

    /** @see FXAA */
    companion object {
        /** TODO */
        context (GLContext, Lifetime)
        fun create(viewportWidth: Float, viewportHeight: Float): FXAA {
            val (shader) = createShaderProgram(
                vertex = VERTEX_FULLSCREEN_PASS_THROUGH_SHADER,
                fragment = FRAGMENT_FXAA_SHADER,
            )
            val viewportSizeUniform = glGetUniformLocation(shader, "u_viewportSize")
            val textureUniform = glGetUniformLocation(shader, "u_texture")
            val showEdgesUniform = glGetUniformLocation(shader, "u_showEdges")
            val fxaaOnUniform = glGetUniformLocation(shader, "u_fxaaOn")
            val lumaThresholdUniform = glGetUniformLocation(shader, "u_lumaThreshold")
            val mulReduceUniform = glGetUniformLocation(shader, "u_mulReduce")
            val minReduceUniform = glGetUniformLocation(shader, "u_minReduce")
            val maxSpanUniform = glGetUniformLocation(shader, "u_maxSpan")

            glProgramUniform2f(shader, viewportSizeUniform, viewportWidth, viewportHeight)

            return FXAA(shader, viewportSizeUniform, textureUniform, showEdgesUniform, fxaaOnUniform, lumaThresholdUniform, mulReduceUniform, minReduceUniform, maxSpanUniform)
        }

        /** TODO */
        context (GLContext, Lifetime)
        fun create(viewportWidth: Int, viewportHeight: Int) =
            create(viewportWidth.toFloat(), viewportHeight.toFloat())
    }
}

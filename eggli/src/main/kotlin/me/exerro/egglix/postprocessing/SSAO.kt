package me.exerro.egglix.postprocessing

import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.*
import me.exerro.eggli.gl.*
import me.exerro.eggli.types.GLShaderProgram
import me.exerro.eggli.types.GLTexture
import me.exerro.eggli.types.GLUniformLocation
import me.exerro.egglix.shader.createShaderProgram
import me.exerro.lifetimes.Lifetime
import kotlin.math.sqrt

// TODO: review https://www.gamedev.net/tutorials/_/technical/graphics-programming-and-theory/a-simple-and-practical-approach-to-ssao-r2753/

/**
 * The [SSAO] class is a utility object that helps render "screen space ambient
 * occlusion" aka SSAO as a shader pass.
 *
 * **WARNING**: you must set the [projection][setProjectionTransform] and
 * [view][setViewTransform] transform values for this to work. Without those
 * set, nothing will be drawn.
 *
 * Some tips on getting good, performant SSAO:
 * * The result is noisy, so blurring is recommended.
 * * You can render lower-resolution SSAO and linearly sample it at higher
 *   resolution.
 * * Adjusting the radius according to your geometry might help get a better
 *   effect.
 *
 * @see bind
 */
class SSAO(
    val shader: GLShaderProgram,
    val noiseTexture: GLTexture,
    val positionTextureUniform: GLUniformLocation,
    val normalTextureUniform: GLUniformLocation,
    val noiseTextureUniform: GLUniformLocation,
    val projectionTransformUniform: GLUniformLocation,
    val viewTransformUniform: GLUniformLocation,
    val sampleKernelUniform: GLUniformLocation,
    val radiusUniform: GLUniformLocation,
    val biasUniform: GLUniformLocation,
    private var noiseTextureUnit: GLActiveTexture,
) {
    /**
     * Bind the effect ready for use by using the SSAO shader and binding the
     * noise texture.
     *
     * To draw AO, draw geometry (e.g. a fullscreen quad) after binding a
     * position and normal texture to the respective texture units.
     *
     *
     * Example usage:
     *
     *     // bind the position and normal textures
     *     glActiveTexture(GL_TEXTURE0)
     *     glBindTexture(GL_TEXTURE_2D, positionTexture)
     *     glActiveTexture(GL_TEXTURE1)
     *     glBindTexture(GL_TEXTURE_2D, normalTexture)
     *     // bind the SSAO effect
     *     ssao.bind()
     *     // draw a quad
     *     quad.draw()
     *     // unbind the SSAO effect and the position and normal textures
     *     ssao.unbind()
     *     glActiveTexture(GL_TEXTURE1)
     *     glBindTexture(GL_TEXTURE_2D)
     *     glActiveTexture(GL_TEXTURE0)
     *     glBindTexture(GL_TEXTURE_2D)
     */
    context (GLContext)
    fun bind() {
        glUseProgram(shader)
        glActiveTexture(noiseTextureUnit)
        glBindTexture(GL_TEXTURE_2D, noiseTexture)
    }

    /**
     * Unbind the effect. Undoes [bind].
     */
    context (GLContext)
    fun unbind() {
        glActiveTexture(noiseTextureUnit)
        glBindTexture(GL_TEXTURE_2D)
        glUseProgram()
    }

    /** @see bind */
    context (GLContext)
    fun bind(fn: () -> Unit) {
        bind()
        try { fn() }
        finally { unbind() }
    }

    context (GLContext)
    fun setPositionTexture(binding: GLActiveTexture) {
        glProgramUniform1i(shader, positionTextureUniform, binding.glValue - GL_TEXTURE0.glValue)
    }

    context (GLContext)
    fun setNormalTexture(binding: GLActiveTexture) {
        glProgramUniform1i(shader, normalTextureUniform, binding.glValue - GL_TEXTURE0.glValue)
    }

    context (GLContext)
    fun setNoiseTexture(binding: GLActiveTexture) {
        glProgramUniform1i(shader, noiseTextureUniform, binding.glValue - GL_TEXTURE0.glValue)
        noiseTextureUnit = binding
    }

    context (GLContext)
    fun setProjectionTransform(values: FloatArray, rowMajor: Boolean = true) {
        require(values.size == 16)

        glUseProgram(shader) {
            glUniformMatrix4fv(projectionTransformUniform, values, transpose = rowMajor)
        }
    }

    context (GLContext)
    fun setViewTransform(values: FloatArray, rowMajor: Boolean = true) {
        require(values.size == 16)

        glUseProgram(shader) {
            glUniformMatrix4fv(viewTransformUniform, values, transpose = rowMajor)
        }
    }

    /**
     * Set the sample kernel of the effect.
     *
     * TODO: (remember and then) explain what the sample kernel is for
     */
    context (GLContext)
    fun setSampleKernel(sampleKernel: FloatArray) {
        glProgramUniform3fv(shader, sampleKernelUniform, sampleKernel)
    }

    /**
     * Randomises the sample kernel with vectors in a hemisphere, biased towards
     * the origin.
     *
     * @see setSampleKernel
     */
    context (GLContext)
    fun setSampleKernel() {
        val sampleKernelData = (0 until SAMPLE_KERNEL_SIZE).flatMap {
            val f = it / (SAMPLE_KERNEL_SIZE - 1).toFloat()
            val x = (Math.random() * 2 - 1).toFloat()
            val y = (Math.random() * 2 - 1).toFloat()
            val z = (Math.random()).toFloat()
            val len = sqrt(x * x + y * y + z * z)
            val s = (0.1f + 0.9f * f * f) / len
            listOf(x * s, y * s, z * s)
        } .toFloatArray()

        setSampleKernel(sampleKernelData)
    }

    /**
     * Set the radius of the effect. A higher radius will result in a "wider"
     * and more spread out area of effect for the AO.
     */
    context (GLContext)
    fun setRadius(radius: Float) {
        glProgramUniform1f(shader, radiusUniform, radius)
    }

    /**
     * Set the bias of the effect. A higher bias will result in a less
     * noticeable effect (only very obscured locations will be darkened). A
     * lower bias will result in a generally darker scene with only convex
     * corners having no occlusion.
     */
    context (GLContext)
    fun setBias(bias: Float) {
        glProgramUniform1f(shader, biasUniform, bias)
    }

    /** @see SSAO */
    companion object {
        // TODO: configurable sample kernel size and noise texture size

        const val SAMPLE_KERNEL_SIZE = 32
        const val NOISE_TEXTURE_SIZE = 16

        /** Create and initialise an [SSAO] instance. */
        context (GLContext, Lifetime)
        fun create(
            positionTexture: GLActiveTexture = GL_TEXTURE0,
            normalTexture: GLActiveTexture = GL_TEXTURE1,
            noiseTexture: GLActiveTexture = GL_TEXTURE5,
            radius: Float = 0.7f,
            bias: Float = 0f,
        ): SSAO {
            val (shader) = createShaderProgram(
                vertex = VERTEX_FULLSCREEN_PASS_THROUGH_SHADER,
                fragment = SSAO_FRAGMENT_SHADER.replace("%SAMPLE_KERNEL_SIZE%", "$SAMPLE_KERNEL_SIZE"),
            )
            val (ssaoNoiseTexture) = glCreateTextures(GL_TEXTURE_2D, label = "SSAO Noise Texture")
            val positionTextureUniform = glGetUniformLocation(shader, "u_position")
            val normalTextureUniform = glGetUniformLocation(shader, "u_normal")
            val noiseTextureUniform = glGetUniformLocation(shader, "u_noise")
            val projectionTransformUniform = glGetUniformLocation(shader, "u_projectionTransform")
            val viewTransformUniform = glGetUniformLocation(shader, "u_viewTransform")
            val sampleKernelUniform = glGetUniformLocation(shader, "u_sampleKernel")
            val radiusUniform = glGetUniformLocation(shader, "u_radius")
            val biasUniform = glGetUniformLocation(shader, "u_bias")

            val ssaoNoiseTextureData = (0 until NOISE_TEXTURE_SIZE * NOISE_TEXTURE_SIZE).flatMap {
                val x = Math.random() * 2 - 1
                val y = Math.random() * 2 - 1
                val z = Math.random() * 2 - 1
                listOf(x.toFloat(), y.toFloat(), z.toFloat())
            } .toFloatArray()

            glTextureStorage2D (ssaoNoiseTexture, 1, GL_RGB16F, NOISE_TEXTURE_SIZE, NOISE_TEXTURE_SIZE)
            glTextureSubImage2D(ssaoNoiseTexture, 0, 0, 0, NOISE_TEXTURE_SIZE, NOISE_TEXTURE_SIZE, GL_RGB, GL_FLOAT, ssaoNoiseTextureData)
            glTextureParameter (ssaoNoiseTexture, GL_TEXTURE_MIN_FILTER, GL_NEAREST)
            glTextureParameter (ssaoNoiseTexture, GL_TEXTURE_MAG_FILTER, GL_NEAREST)
            glTextureParameter (ssaoNoiseTexture, GL_TEXTURE_WRAP_S, GL_REPEAT)
            glTextureParameter (ssaoNoiseTexture, GL_TEXTURE_WRAP_T, GL_REPEAT)

            glProgramUniform1i(shader, positionTextureUniform, positionTexture.glValue - GL_TEXTURE0.glValue)
            glProgramUniform1i(shader, normalTextureUniform, normalTexture.glValue - GL_TEXTURE0.glValue)
            glProgramUniform1i(shader, noiseTextureUniform, noiseTexture.glValue - GL_TEXTURE0.glValue)
            // no projection/view transform given here since we can't get any sensible values for those
            // sample kernel is set below
            glProgramUniform1f(shader, radiusUniform, radius)
            glProgramUniform1f(shader, biasUniform, bias)

            return SSAO(
                shader,
                ssaoNoiseTexture,
                positionTextureUniform,
                normalTextureUniform,
                noiseTextureUniform,
                projectionTransformUniform,
                viewTransformUniform,
                sampleKernelUniform,
                radiusUniform,
                biasUniform,
                noiseTexture,
            ).also {
                it.setSampleKernel()
            }
        }
    }
}

// TODO: this still has some issues at edges of geometry (when no background)!
private const val SSAO_FRAGMENT_SHADER = """
#version 460 core

uniform sampler2D u_position;
uniform sampler2D u_normal;
uniform sampler2D u_noise;

uniform mat4 u_projectionTransform;
uniform mat4 u_viewTransform;

uniform vec3 u_sampleKernel[%SAMPLE_KERNEL_SIZE%];
uniform float u_radius;
uniform float u_bias;

in vec2 f_uv;

out vec4 o_colour;

void main() {
    vec3 position = texture(u_position, f_uv).xyz;
    vec3 normal = normalize(texture(u_normal, f_uv).xyz);
    vec3 random = texture(u_noise, gl_FragCoord.xy / textureSize(u_noise, 0)).xyz;
    
    vec3 tangent   = normalize(random - normal * dot(random, normal));
    vec3 biTangent = cross(normal, tangent);
    mat3 TBN       = mat3(tangent, biTangent, normal);

    float occlusion = 0;

    for(int i = 0; i < %SAMPLE_KERNEL_SIZE%; ++i)
    {
        vec4 samplePosition = vec4(position + TBN * u_sampleKernel[i] * u_radius, 1);
        samplePosition = u_viewTransform * samplePosition;
        vec4 offsetUV     = samplePosition;
             offsetUV     = u_projectionTransform * offsetUV;
             offsetUV.xy /= offsetUV.w;
             offsetUV.xy  = offsetUV.xy * 0.5 + 0.5;
        
        float sampleDepth = (u_viewTransform * texture(u_position, offsetUV.xy)).z;
        float fragmentDepth = (u_viewTransform * vec4(position, 1)).z;
        float rangeCheck = smoothstep(0.0, 1.0, u_radius / abs(fragmentDepth - sampleDepth));
        occlusion += (sampleDepth >= samplePosition.z + u_bias ? 1.0 : 0.0) * rangeCheck;
    }
    
    occlusion = 1 - occlusion / %SAMPLE_KERNEL_SIZE%;
    o_colour = vec4(vec3(occlusion), 1);
}
"""

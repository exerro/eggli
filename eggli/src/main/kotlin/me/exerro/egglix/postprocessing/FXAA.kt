package me.exerro.egglix.postprocessing

import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.GLActiveTexture
import me.exerro.eggli.enum.GL_TEXTURE0
import me.exerro.eggli.gl.*
import me.exerro.eggli.types.GLShaderProgram
import me.exerro.eggli.types.GLUniformLocation
import me.exerro.egglix.shader.createShaderProgram
import me.exerro.lifetimes.Lifetime

/**
 * The [FXAA] class is a utility object that helps render "fast approximate anti
 * aliasing" aka FXAA as a shader pass using an aliased input texture.
 *
 * see FXAA
 * http://developer.download.nvidia.com/assets/gamedev/files/sdk/11/FXAA_WhitePaper.pdf
 * http://iryoku.com/aacourse/downloads/09-FXAA-3.11-in-15-Slides.pdf
 * http://horde3d.org/wiki/index.php5?title=Shading_Technique_-_FXAA
 *
 * ```kotlin
 * fxaa.shader.bind {
 *     // .. draw texture using fullscreen quad ..
 * }
 * ```
 */
class FXAA(
    val shader: GLShaderProgram,
    val textureUniform: GLUniformLocation,
    val viewportSizeUniform: GLUniformLocation,
    val showEdgesUniform: GLUniformLocation,
    val lumaThresholdUniform: GLUniformLocation,
    val mulReduceUniform: GLUniformLocation,
    val minReduceUniform: GLUniformLocation,
    val maxSpanUniform: GLUniformLocation,
) {
    /**
     * Bind the effect ready for use. Should manually draw the region that has
     * the effect applied.
     */
    context (GLContext)
    fun bind() {
        glUseProgram(shader)
    }

    /**
     * Bind the effect ready for use within [fn], unbinding afterwards. Should
     * manually draw the region that has the effect applied.
     */
    context (GLContext)
    fun bind(fn: () -> Unit) {
        glUseProgram(shader, fn)
    }

    /** Unbind the effect after [bind] was called. */
    context (GLContext)
    fun unbind() {
        glUseProgram()
    }

    /** Set the texture read from when applying AA. */
    context (GLContext)
    fun setInputTexture(binding: GLActiveTexture) {
        glProgramUniform1i(shader, textureUniform, binding.glValue - GL_TEXTURE0.glValue)
    }

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
    fun setShowEdges(showEdges: Boolean) {
        glProgramUniform1i(shader, showEdgesUniform, showEdges)
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
        fun create(
            viewportWidth: Float,
            viewportHeight: Float,
            showEdges: Boolean = false,
            lumaThreshold: Float = 0.02f,
            mulReduce: Float = 0.125f,
            minReduce: Float = 0.5f, // 1/128f
            maxSpan: Float = 8f,
        ): FXAA {
            val (shader) = createShaderProgram(
                vertex = VERTEX_FULLSCREEN_PASS_THROUGH_SHADER,
                fragment = FXAA_FRAGMENT_SHADER,
            )
            val textureUniform = glGetUniformLocation(shader, "u_texture")
            val viewportSizeUniform = glGetUniformLocation(shader, "u_viewportSize")
            val showEdgesUniform = glGetUniformLocation(shader, "u_showEdges")
            val lumaThresholdUniform = glGetUniformLocation(shader, "u_lumaThreshold")
            val mulReduceUniform = glGetUniformLocation(shader, "u_mulReduce")
            val minReduceUniform = glGetUniformLocation(shader, "u_minReduce")
            val maxSpanUniform = glGetUniformLocation(shader, "u_maxSpan")

            glProgramUniform2f(shader, viewportSizeUniform, viewportWidth, viewportHeight)
            glProgramUniform1i(shader, showEdgesUniform, showEdges)
            glProgramUniform1f(shader, lumaThresholdUniform, lumaThreshold)
            glProgramUniform1f(shader, mulReduceUniform, mulReduce)
            glProgramUniform1f(shader, minReduceUniform, minReduce)
            glProgramUniform1f(shader, maxSpanUniform, maxSpan)

            return FXAA(shader, textureUniform, viewportSizeUniform, showEdgesUniform, lumaThresholdUniform, mulReduceUniform, minReduceUniform, maxSpanUniform)
        }

        /** TODO */
        context (GLContext, Lifetime)
        fun create(
            viewportWidth: Int,
            viewportHeight: Int,
            showEdges: Boolean = false,
            lumaThreshold: Float = 0.04f,
            mulReduce: Float = 0.125f,
            minReduce: Float = 0.5f, // 1/128f
            maxSpan: Float = 8f,
        ) =
            create(viewportWidth.toFloat(), viewportHeight.toFloat(), showEdges, lumaThreshold, mulReduce, minReduce, maxSpan)
    }
}

/**
 * Fragment shader implementing FXAA (fast approximate anti aliasing).
 * Full credit to https://github.com/McNopper/OpenGL/tree/master/Example42 !
 * Ty dude you saved me.
 */
private const val FXAA_FRAGMENT_SHADER = """
#version 460 core

layout (binding = 0) uniform sampler2D u_texture;

uniform vec2 u_viewportSize;
uniform int u_showEdges;
uniform float u_lumaThreshold;
uniform float u_mulReduce;
uniform float u_minReduce;
uniform float u_maxSpan;

in vec2 f_uv;

out vec4 o_colour;

void main(void)
{
    vec3 rgbM = texture(u_texture, f_uv).rgb;

    vec2 texelStep = 1.0 / u_viewportSize.xy;

	// Sampling neighbour texels. Offsets are adapted to OpenGL texture coordinates. 
	vec3 rgbNW = textureOffset(u_texture, f_uv, ivec2(-1, 1)).rgb;
    vec3 rgbNE = textureOffset(u_texture, f_uv, ivec2(1, 1)).rgb;
    vec3 rgbSW = textureOffset(u_texture, f_uv, ivec2(-1, -1)).rgb;
    vec3 rgbSE = textureOffset(u_texture, f_uv, ivec2(1, -1)).rgb;

	// see http://en.wikipedia.org/wiki/Grayscale
	const vec3 toLuma = vec3(0.299, 0.587, 0.114);
	
	// Convert from RGB to luma.
	float lumaNW = dot(rgbNW, toLuma);
	float lumaNE = dot(rgbNE, toLuma);
	float lumaSW = dot(rgbSW, toLuma);
	float lumaSE = dot(rgbSE, toLuma);
	float lumaM = dot(rgbM, toLuma);

	// Gather minimum and maximum luma.
	float lumaMin = min(lumaM, min(min(lumaNW, lumaNE), min(lumaSW, lumaSE)));
	float lumaMax = max(lumaM, max(max(lumaNW, lumaNE), max(lumaSW, lumaSE)));
	
	// If contrast is lower than a maximum threshold ...
	if (lumaMax - lumaMin <= lumaMax * u_lumaThreshold)
	{
		// ... do no AA and return.
		o_colour = vec4(rgbM, 1.0);

		return;
	}
	
	// Sampling is done along the gradient.
	vec2 samplingDirection;
	samplingDirection.x = -((lumaNW + lumaNE) - (lumaSW + lumaSE));
    samplingDirection.y =  ((lumaNW + lumaSW) - (lumaNE + lumaSE));
    
    // Sampling step distance depends on the luma: The brighter the sampled texels, the smaller the final sampling step direction.
    // This results, that brighter areas are less blurred/more sharper than dark areas.  
    float samplingDirectionReduce = max((lumaNW + lumaNE + lumaSW + lumaSE) * 0.25 * u_mulReduce, u_minReduce);

	// Factor for norming the sampling direction plus adding the brightness influence. 
	float minSamplingDirectionFactor = 1.0 / (min(abs(samplingDirection.x), abs(samplingDirection.y)) + samplingDirectionReduce);
    
    // Calculate final sampling direction vector by reducing, clamping to a range and finally adapting to the texture size. 
    samplingDirection = clamp(samplingDirection * minSamplingDirectionFactor, vec2(-u_maxSpan), vec2(u_maxSpan)) * texelStep;
	
	// Inner samples on the tab.
	vec3 rgbSampleNeg = texture(u_texture, f_uv + samplingDirection * (1.0/3.0 - 0.5)).rgb;
	vec3 rgbSamplePos = texture(u_texture, f_uv + samplingDirection * (2.0/3.0 - 0.5)).rgb;

	vec3 rgbTwoTab = (rgbSamplePos + rgbSampleNeg) * 0.5;  

	// Outer samples on the tab.
	vec3 rgbSampleNegOuter = texture(u_texture, f_uv + samplingDirection * (0.0/3.0 - 0.5)).rgb;
	vec3 rgbSamplePosOuter = texture(u_texture, f_uv + samplingDirection * (3.0/3.0 - 0.5)).rgb;
	
	vec3 rgbFourTab = (rgbSamplePosOuter + rgbSampleNegOuter) * 0.25 + rgbTwoTab * 0.5;   
	
	// Calculate luma for checking against the minimum and maximum value.
	float lumaFourTab = dot(rgbFourTab, toLuma);
	
	// Are outer samples of the tab beyond the edge ... 
	if (lumaFourTab < lumaMin || lumaFourTab > lumaMax)
	{
		// ... yes, so use only two samples.
		o_colour = vec4(rgbTwoTab, 1.0); 
	}
	else
	{
		// ... no, so use four samples. 
		o_colour = vec4(rgbFourTab, 1.0);
	}

	// Show edges for debug purposes.	
	if (u_showEdges != 0)
	{
		o_colour.r = 1.0;
	}
}
"""

package me.exerro.egglix.postprocessing

import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.GLActiveTexture
import me.exerro.eggli.gl.*
import me.exerro.eggli.types.GLShaderProgram
import me.exerro.eggli.types.GLUniformLocation
import me.exerro.egglix.shader.createShaderProgram
import me.exerro.lifetimes.Lifetime

/**
 * The [FXAA] class is a utility object that helps render "fast approximate anti
 * aliasing" aka FXAA as a shader pass using an aliased input texture.
 *
 * ```kotlin
 * glUseProgram(fxaa.shader) {
 *     // .. draw texture ..
 * }
 * ```
 */
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
    fun setInputTexture(binding: GLActiveTexture) {
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
                fragment = FXAA_FRAGMENT_SHADER,
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

/**
 * Fragment shader implementing FXAA (fast approximate anti aliasing).
 * Full credit to https://github.com/McNopper/OpenGL/tree/master/Example42 !
 * Ty dude you saved me.
 *
 * Uniforms:
 * * `u_showEdges` - 0 or 1 - highlights edges when set to 1
 * * `u_fxaaOn` - 0 or 1 - enables or disables anti aliasing
 * * `u_lumaThreshold` - ?
 * * `u_mulReduce` - ?
 * * `u_minReduce` - ?
 * * `u_maxSpan` - ?
 */
private const val FXAA_FRAGMENT_SHADER = """
#version 460 core

layout (binding = 0) uniform sampler2D u_texture;

uniform vec2 u_viewportSize;

uniform int u_showEdges = 0;
uniform int u_fxaaOn = 1;

//uniform float u_lumaThreshold = 0.04;
//uniform float u_mulReduce = 0.125;
//uniform float u_minReduce = 1/128;
//uniform float u_maxSpan = 8.0;

uniform float u_lumaThreshold = 0.04;
uniform float u_mulReduce = 0.125;
uniform float u_minReduce = 1/2;
uniform float u_maxSpan = 8.0;

in vec2 f_uv;

out vec4 o_colour;

// see FXAA
// http://developer.download.nvidia.com/assets/gamedev/files/sdk/11/FXAA_WhitePaper.pdf
// http://iryoku.com/aacourse/downloads/09-FXAA-3.11-in-15-Slides.pdf
// http://horde3d.org/wiki/index.php5?title=Shading_Technique_-_FXAA

void main(void)
{
    vec3 rgbM = texture(u_texture, f_uv).rgb;

	// Possibility to toggle FXAA on and off.
	if (u_fxaaOn == 0)
	{
		o_colour = vec4(rgbM, 1.0);
		return;
	}

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

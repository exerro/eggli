package me.exerro.eggli.enum

import org.lwjgl.opengl.GL46C

/** TODO */
enum class GLTarget(val glValue: Int, val glName: String) {
    /** If enabled, blend the computed fragment color values with the values in the color buffers. See glBlendFunc. */
    Blend(GL46C.GL_BLEND, glName = "GL_BLEND"),

    /** If enabled, clip geometry against user-defined half space 0. */
    ClipDistance0(GL46C.GL_CLIP_DISTANCE0, glName = "GL_CLIP_DISTANCE"),

    /** If enabled, clip geometry against user-defined half space 1. */
    ClipDistance1(GL46C.GL_CLIP_DISTANCE1, glName = "GL_CLIP_DISTANCE"),

    /** If enabled, clip geometry against user-defined half space 2. */
    ClipDistance2(GL46C.GL_CLIP_DISTANCE0, glName = "GL_CLIP_DISTANCE"),

    /** If enabled, clip geometry against user-defined half space 3. */
    ClipDistance3(GL46C.GL_CLIP_DISTANCE0, glName = "GL_CLIP_DISTANCE"),

    /** If enabled, clip geometry against user-defined half space 4. */
    ClipDistance4(GL46C.GL_CLIP_DISTANCE0, glName = "GL_CLIP_DISTANCE"),

    /** If enabled, clip geometry against user-defined half space 5. */
    ClipDistance5(GL46C.GL_CLIP_DISTANCE0, glName = "GL_CLIP_DISTANCE"),

    /** If enabled, clip geometry against user-defined half space 6. */
    ClipDistance6(GL46C.GL_CLIP_DISTANCE0, glName = "GL_CLIP_DISTANCE"),

    /** If enabled, clip geometry against user-defined half space 7. */
    ClipDistance7(GL46C.GL_CLIP_DISTANCE0, glName = "GL_CLIP_DISTANCE"),

    /** If enabled, apply the currently selected logical operation to the computed fragment color and color buffer values. See glLogicOp. */
    ColorLogicOp(GL46C.GL_COLOR_LOGIC_OP, glName = "GL_COLOR_LOGIC_OP"),

    /** If enabled, cull polygons based on their winding in window coordinates. See glCullFace. */
    CullFace(GL46C.GL_CULL_FACE, glName = "GL_CULL_FACE"),

    /** If enabled, debug messages are produced by a debug context. When disabled, the debug message log is silenced. Note that in a non-debug context, very few, if any messages might be produced, even when GL_DEBUG_OUTPUT is enabled. */
    DebugOutput(GL46C.GL_DEBUG_OUTPUT, glName = "GL_DEBUG_OUTPUT"),

    /** If enabled, debug messages are produced synchronously by a debug context. If disabled, debug messages may be produced asynchronously. In particular, they may be delayed relative to the execution of GL commands, and the debug callback function may be called from a thread other than that in which the commands are executed. See glDebugMessageCallback. */
    DebugOutputSynchronous(GL46C.GL_DEBUG_OUTPUT_SYNCHRONOUS, glName = "GL_DEBUG_OUTPUT_SYNCHRONOUS"),

    /** If enabled, the −wc≤zc≤wc plane equation is ignored by view volume clipping (effectively, there is no near or far plane clipping). See glDepthRange. */
    DepthClamp(GL46C.GL_DEPTH_CLAMP, glName = "GL_DEPTH_CLAMP"),

    /** If enabled, do depth comparisons and update the depth buffer. Note that even if the depth buffer exists and the depth mask is non-zero, the depth buffer is not updated if the depth test is disabled. See glDepthFunc and glDepthRange. */
    DepthTest(GL46C.GL_DEPTH_TEST, glName = "GL_DEPTH_TEST"),

    /** If enabled, dither color components or indices before they are written to the color buffer. */
    Dither(GL46C.GL_DITHER, glName = "GL_DITHER"),

    /** If enabled and the value of GL_FRAMEBUFFER_ATTACHMENT_COLOR_ENCODING for the framebuffer attachment corresponding to the destination buffer is GL_SRGB, the R, G, and B destination color values (after conversion from fixed-point to floating-point) are considered to be encoded for the sRGB color space and hence are linearized prior to their use in blending. */
    FramebufferSRGB(GL46C.GL_FRAMEBUFFER_SRGB, glName = "GL_FRAMEBUFFER_SRGB"),

    /** If enabled, draw lines with correct filtering. Otherwise, draw aliased lines. See glLineWidth. */
    LineSmooth(GL46C.GL_LINE_SMOOTH, glName = "GL_LINE_SMOOTH"),

    /** If enabled, use multiple fragment samples in computing the final color of a pixel. See glSampleCoverage. */
    Multisample(GL46C.GL_MULTISAMPLE, glName = "GL_MULTISAMPLE"),

    /** If enabled, and if the polygon is rendered in GL_FILL mode, an offset is added to depth values of a polygon's fragments before the depth comparison is performed. See glPolygonOffset. */
    PolygonOffsetFill(GL46C.GL_POLYGON_OFFSET_FILL, glName = "GL_POLYGON_OFFSET_FILL"),

    /** If enabled, and if the polygon is rendered in GL_LINE mode, an offset is added to depth values of a polygon's fragments before the depth comparison is performed. See glPolygonOffset. */
    PolygonOffsetLine(GL46C.GL_POLYGON_OFFSET_LINE, glName = "GL_POLYGON_OFFSET_LINE"),

    /** If enabled, an offset is added to depth values of a polygon's fragments before the depth comparison is performed, if the polygon is rendered in GL_POINT mode. See glPolygonOffset. */
    PolygonOffsetPoint(GL46C.GL_POLYGON_OFFSET_POINT, glName = "GL_POLYGON_OFFSET_POINT"),

    /** If enabled, draw polygons with proper filtering. Otherwise, draw aliased polygons. For correct antialiased polygons, an alpha buffer is needed and the polygons must be sorted front to back. */
    PolygonSmooth(GL46C.GL_POLYGON_SMOOTH, glName = "GL_POLYGON_SMOOTH"),

    /** Enables primitive restarting. If enabled, any one of the draw commands which transfers a set of generic attribute array elements to the GL will restart the primitive when the index of the vertex is equal to the primitive restart index. See glPrimitiveRestartIndex. */
    PrimitiveRestart(GL46C.GL_PRIMITIVE_RESTART, glName = "GL_PRIMITIVE_RESTART"),

    /** Enables primitive restarting with a fixed index. If enabled, any one of the draw commands which transfers a set of generic attribute array elements to the GL will restart the primitive when the index of the vertex is equal to the fixed primitive index for the specified index type. The fixed index is equal to 2n−1 where n is equal to 8 for GL_UNSIGNED_BYTE, 16 for GL_UNSIGNED_SHORT and 32 for GL_UNSIGNED_INT. */
    PrimitiveRestartFixedIndex(GL46C.GL_PRIMITIVE_RESTART_FIXED_INDEX, glName = "GL_PRIMITIVE_RESTART_FIXED_INDEX"),

    /** If enabled, primitives are discarded after the optional transform feedback stage, but before rasterization. Furthermore, when enabled, glClear, glClearBufferData, glClearBufferSubData, glClearTexImage, and glClearTexSubImage are ignored. */
    RasterizerDiscard(GL46C.GL_RASTERIZER_DISCARD, glName = "GL_RASTERIZER_DISCARD"),

    /** If enabled, compute a temporary coverage value where each bit is determined by the alpha value at the corresponding sample location. The temporary coverage value is then ANDed with the fragment coverage value. */
    SampleAlphaToCoverage(GL46C.GL_SAMPLE_ALPHA_TO_COVERAGE, glName = "GL_SAMPLE_ALPHA_TO_COVERAGE"),

    /** If enabled, each sample alpha value is replaced by the maximum representable alpha value. */
    SampleAlphaToOne(GL46C.GL_SAMPLE_ALPHA_TO_ONE, glName = "GL_SAMPLE_ALPHA_TO_ONE"),

    /** If enabled, the fragment's coverage is ANDed with the temporary coverage value. If GL_SAMPLE_COVERAGE_INVERT is set to GL_TRUE, invert the coverage value. See glSampleCoverage. */
    SampleCoverage(GL46C.GL_SAMPLE_COVERAGE, glName = "GL_SAMPLE_COVERAGE"),

    /** If enabled, the active fragment shader is run once for each covered sample, or at fraction of this rate as determined by the current value of GL_MIN_SAMPLE_SHADING_VALUE. See glMinSampleShading. */
    SampleShading(GL46C.GL_SAMPLE_SHADING, glName = "GL_SAMPLE_SHADING"),

    /** If enabled, the sample coverage mask generated for a fragment during rasterization will be ANDed with the value of GL_SAMPLE_MASK_VALUE before shading occurs. See glSampleMaski. */
    SampleMask(GL46C.GL_SAMPLE_MASK, glName = "GL_SAMPLE_MASK"),

    /** If enabled, discard fragments that are outside the scissor rectangle. See glScissor. */
    ScissorTest(GL46C.GL_SCISSOR_TEST, glName = "GL_SCISSOR_TEST"),

    /** If enabled, do stencil testing and update the stencil buffer. See glStencilFunc and glStencilOp. */
    StencilTest(GL46C.GL_STENCIL_TEST, glName = "GL_STENCIL_TEST"),

    /** If enabled, cubemap textures are sampled such that when linearly sampling from the border between two adjacent faces, texels from both faces are used to generate the final sample value. When disabled, texels from only a single face are used to construct the final sample value. */
    TextureCubeMapSeamless(GL46C.GL_TEXTURE_CUBE_MAP_SEAMLESS, glName = "GL_TEXTURE_CUBE_MAP_SEAMLESS"),

    /** If enabled and a vertex or geometry shader is active, then the derived point size is taken from the (potentially clipped) shader builtin gl_PointSize and clamped to the implementation-dependent point size range. */
    ProgramPointSize(GL46C.GL_PROGRAM_POINT_SIZE, glName = "GL_PROGRAM_POINT_SIZE");

    override fun toString() = glName

    /** @see GLTarget */
    companion object {
        /** TODO */
        fun fromGLValue(glValue: Int) = values()
            .firstOrNull { it.glValue == glValue }
    }
}

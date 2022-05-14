package me.exerro.eggli.gen

import com.squareup.kotlinpoet.*
import kotlin.reflect.KProperty

@Suppress("LocalVariableName", "UNUSED_VARIABLE")
private val enumData = createEnumData {
    val GLActiveTexture by options {
        val GL_TEXTURE0 by noDocumentation
        val GL_TEXTURE1 by noDocumentation
        val GL_TEXTURE2 by noDocumentation
        val GL_TEXTURE3 by noDocumentation
        val GL_TEXTURE4 by noDocumentation
        val GL_TEXTURE5 by noDocumentation
        val GL_TEXTURE6 by noDocumentation
        val GL_TEXTURE7 by noDocumentation
        val GL_TEXTURE8 by noDocumentation
        val GL_TEXTURE9 by noDocumentation
        val GL_TEXTURE10 by noDocumentation
        val GL_TEXTURE11 by noDocumentation
        val GL_TEXTURE12 by noDocumentation
        val GL_TEXTURE13 by noDocumentation
        val GL_TEXTURE14 by noDocumentation
        val GL_TEXTURE15 by noDocumentation
        val GL_TEXTURE16 by noDocumentation
        val GL_TEXTURE17 by noDocumentation
        val GL_TEXTURE18 by noDocumentation
        val GL_TEXTURE19 by noDocumentation
        val GL_TEXTURE20 by noDocumentation
        val GL_TEXTURE21 by noDocumentation
        val GL_TEXTURE22 by noDocumentation
        val GL_TEXTURE23 by noDocumentation
        val GL_TEXTURE24 by noDocumentation
        val GL_TEXTURE25 by noDocumentation
        val GL_TEXTURE26 by noDocumentation
        val GL_TEXTURE27 by noDocumentation
        val GL_TEXTURE28 by noDocumentation
        val GL_TEXTURE29 by noDocumentation
        val GL_TEXTURE30 by noDocumentation
        val GL_TEXTURE31 by noDocumentation

        "A texture unit accepted by the texture parameter of glActiveTexture and" +
                "glMultiTexCoord."
    }

    val GLBlendEquation by options {
        val GL_FUNC_ADD by "the default, adds both colors to each other: C_{result}=Src+Dst."
        val GL_FUNC_SUBTRACT by "subtracts both colors from each other: C_{result}=Src−Dst."
        val GL_FUNC_REVERSE_SUBTRACT by "subtracts both colors, but reverses order: C_{result}=Dst−Src."
        val GL_MIN by "takes the component-wise minimum of both colors: C_{result}=min(Dst,Src)."
        val GL_MAX by "takes the component-wise maximum of both colors: C_{result}=max(Dst,Src)."

        noDocumentation
    }

    val GLBlendFactor by options {
        val GL_ZERO by "Factor is equal to 0."
        val GL_ONE by "Factor is equal to 1."
        val GL_SRC_COLOR by "Factor is equal to the source color vector C_{source}."
        val GL_ONE_MINUS_SRC_COLOR by "Factor is equal to 1 minus the source color vector: 1−C_{source}."
        val GL_DST_COLOR by "Factor is equal to the destination color vector C_{destination}"
        val GL_ONE_MINUS_DST_COLOR by "Factor is equal to 1 minus the destination color vector: 1−C_{destination}."
        val GL_SRC_ALPHA by "Factor is equal to the alpha component of the source color vector C_{source}."
        val GL_ONE_MINUS_SRC_ALPHA by "Factor is equal to 1−alpha of the source color vector C_{source}."
        val GL_DST_ALPHA by "Factor is equal to the alpha component of the destination color vector C_{destination}."
        val GL_ONE_MINUS_DST_ALPHA by "Factor is equal to 1−alpha of the destination color vector C_{destination}."
        val GL_CONSTANT_COLOR by "Factor is equal to the constant color vector C_{constant}."
        val GL_ONE_MINUS_CONSTANT_COLOR by "Factor is equal to 1 - the constant color vector C_{constant}."
        val GL_CONSTANT_ALPHA by "Factor is equal to the alpha component of the constant color vector C_{constant}."
        val GL_ONE_MINUS_CONSTANT_ALPHA by "Factor is equal to 1−alpha of the constant color vector C_{constant}."

        noDocumentation
    }

    val GLBufferTarget by options {
        val GL_ARRAY_BUFFER by noDocumentation
        val GL_ATOMIC_COUNTER_BUFFER by noDocumentation
        val GL_COPY_READ_BUFFER by noDocumentation
        val GL_COPY_WRITE_BUFFER by noDocumentation
        val GL_DRAW_INDIRECT_BUFFER by noDocumentation
        val GL_DISPATCH_INDIRECT_BUFFER by noDocumentation
        val GL_ELEMENT_ARRAY_BUFFER by noDocumentation
        val GL_PIXEL_PACK_BUFFER by noDocumentation
        val GL_PIXEL_UNPACK_BUFFER by noDocumentation
        val GL_QUERY_BUFFER by noDocumentation
        val GL_SHADER_STORAGE_BUFFER by noDocumentation
        val GL_TEXTURE_BUFFER by noDocumentation
        val GL_TRANSFORM_FEEDBACK_BUFFER by noDocumentation
        val GL_UNIFORM_BUFFER by noDocumentation

        noDocumentation
    }

    val GLBufferUsage by options {
        val GL_STREAM_DRAW by noDocumentation
        val GL_STREAM_READ by noDocumentation
        val GL_STREAM_COPY by noDocumentation
        val GL_STATIC_DRAW by noDocumentation
        val GL_STATIC_READ by noDocumentation
        val GL_STATIC_COPY by noDocumentation
        val GL_DYNAMIC_DRAW by noDocumentation
        val GL_DYNAMIC_READ by noDocumentation
        val GL_DYNAMIC_COPY by noDocumentation

        noDocumentation
    }

    val GLColorAttachment by options {
        val GL_COLOR_ATTACHMENT0 by noDocumentation
        val GL_COLOR_ATTACHMENT1 by noDocumentation
        val GL_COLOR_ATTACHMENT2 by noDocumentation
        val GL_COLOR_ATTACHMENT3 by noDocumentation
        val GL_COLOR_ATTACHMENT4 by noDocumentation
        val GL_COLOR_ATTACHMENT5 by noDocumentation
        val GL_COLOR_ATTACHMENT6 by noDocumentation
        val GL_COLOR_ATTACHMENT7 by noDocumentation
        val GL_COLOR_ATTACHMENT8 by noDocumentation
        val GL_COLOR_ATTACHMENT9 by noDocumentation
        val GL_COLOR_ATTACHMENT10 by noDocumentation
        val GL_COLOR_ATTACHMENT11 by noDocumentation
        val GL_COLOR_ATTACHMENT12 by noDocumentation
        val GL_COLOR_ATTACHMENT13 by noDocumentation
        val GL_COLOR_ATTACHMENT14 by noDocumentation
        val GL_COLOR_ATTACHMENT15 by noDocumentation
        val GL_COLOR_ATTACHMENT16 by noDocumentation
        val GL_COLOR_ATTACHMENT17 by noDocumentation
        val GL_COLOR_ATTACHMENT18 by noDocumentation
        val GL_COLOR_ATTACHMENT19 by noDocumentation
        val GL_COLOR_ATTACHMENT20 by noDocumentation
        val GL_COLOR_ATTACHMENT21 by noDocumentation
        val GL_COLOR_ATTACHMENT22 by noDocumentation
        val GL_COLOR_ATTACHMENT23 by noDocumentation
        val GL_COLOR_ATTACHMENT24 by noDocumentation
        val GL_COLOR_ATTACHMENT25 by noDocumentation
        val GL_COLOR_ATTACHMENT26 by noDocumentation
        val GL_COLOR_ATTACHMENT27 by noDocumentation
        val GL_COLOR_ATTACHMENT28 by noDocumentation
        val GL_COLOR_ATTACHMENT29 by noDocumentation
        val GL_COLOR_ATTACHMENT30 by noDocumentation
        val GL_COLOR_ATTACHMENT31 by noDocumentation
        val GL_DEPTH_ATTACHMENT by noDocumentation
        val GL_STENCIL_ATTACHMENT by noDocumentation
        val GL_DEPTH_STENCIL_ATTACHMENT by noDocumentation

        noDocumentation
    }

    val GLCullFace by options {
        val GL_BACK by "Culls only the back faces."
        val GL_FRONT by "Culls only the front faces."
        val GL_FRONT_AND_BACK by "Culls both the front and back faces."

        noDocumentation
    }

    val GLDepthFunction by options {
        val GL_ALWAYS by "The depth test always passes."
        val GL_NEVER by "The depth test never passes."
        val GL_LESS by "Passes if the fragment's depth value is less than the stored depth value."
        val GL_EQUAL by "Passes if the fragment's depth value is equal to the stored depth value."
        val GL_LEQUAL by "Passes if the fragment's depth value is less than or equal to the stored depth value."
        val GL_GREATER by "Passes if the fragment's depth value is greater than the stored depth value."
        val GL_NOTEQUAL by "Passes if the fragment's depth value is not equal to the stored depth value."
        val GL_GEQUAL by "Passes if the fragment's depth value is greater than or equal to the stored depth value."

        noDocumentation
    }

    val GLDrawBuffer by options {
        val GL_NONE by "The fragment shader output value is not written into any color buffer."
        val GL_FRONT_LEFT by "The fragment shader output value is written into the front left color buffer."
        val GL_FRONT_RIGHT by "The fragment shader output value is written into the front right color buffer."
        val GL_BACK_LEFT by "The fragment shader output value is written into the back left color buffer."
        val GL_BACK_RIGHT by "The fragment shader output value is written into the back right color buffer."
        val GL_COLOR_ATTACHMENT0 by "The fragment shader output value is written into the nth color attachment of the current framebuffer. n may range from zero to the value of GL_MAX_COLOR_ATTACHMENTS."
        val GL_COLOR_ATTACHMENT1 by "The fragment shader output value is written into the nth color attachment of the current framebuffer. n may range from zero to the value of GL_MAX_COLOR_ATTACHMENTS."
        val GL_COLOR_ATTACHMENT2 by "The fragment shader output value is written into the nth color attachment of the current framebuffer. n may range from zero to the value of GL_MAX_COLOR_ATTACHMENTS."
        val GL_COLOR_ATTACHMENT3 by "The fragment shader output value is written into the nth color attachment of the current framebuffer. n may range from zero to the value of GL_MAX_COLOR_ATTACHMENTS."
        val GL_COLOR_ATTACHMENT4 by "The fragment shader output value is written into the nth color attachment of the current framebuffer. n may range from zero to the value of GL_MAX_COLOR_ATTACHMENTS."
        val GL_COLOR_ATTACHMENT5 by "The fragment shader output value is written into the nth color attachment of the current framebuffer. n may range from zero to the value of GL_MAX_COLOR_ATTACHMENTS."
        val GL_COLOR_ATTACHMENT6 by "The fragment shader output value is written into the nth color attachment of the current framebuffer. n may range from zero to the value of GL_MAX_COLOR_ATTACHMENTS."
        val GL_COLOR_ATTACHMENT7 by "The fragment shader output value is written into the nth color attachment of the current framebuffer. n may range from zero to the value of GL_MAX_COLOR_ATTACHMENTS."
        val GL_COLOR_ATTACHMENT8 by "The fragment shader output value is written into the nth color attachment of the current framebuffer. n may range from zero to the value of GL_MAX_COLOR_ATTACHMENTS."
        val GL_COLOR_ATTACHMENT9 by "The fragment shader output value is written into the nth color attachment of the current framebuffer. n may range from zero to the value of GL_MAX_COLOR_ATTACHMENTS."
        val GL_COLOR_ATTACHMENT10 by "The fragment shader output value is written into the nth color attachment of the current framebuffer. n may range from zero to the value of GL_MAX_COLOR_ATTACHMENTS."
        val GL_COLOR_ATTACHMENT11 by "The fragment shader output value is written into the nth color attachment of the current framebuffer. n may range from zero to the value of GL_MAX_COLOR_ATTACHMENTS."
        val GL_COLOR_ATTACHMENT12 by "The fragment shader output value is written into the nth color attachment of the current framebuffer. n may range from zero to the value of GL_MAX_COLOR_ATTACHMENTS."
        val GL_COLOR_ATTACHMENT13 by "The fragment shader output value is written into the nth color attachment of the current framebuffer. n may range from zero to the value of GL_MAX_COLOR_ATTACHMENTS."
        val GL_COLOR_ATTACHMENT14 by "The fragment shader output value is written into the nth color attachment of the current framebuffer. n may range from zero to the value of GL_MAX_COLOR_ATTACHMENTS."
        val GL_COLOR_ATTACHMENT15 by "The fragment shader output value is written into the nth color attachment of the current framebuffer. n may range from zero to the value of GL_MAX_COLOR_ATTACHMENTS."
        val GL_COLOR_ATTACHMENT16 by "The fragment shader output value is written into the nth color attachment of the current framebuffer. n may range from zero to the value of GL_MAX_COLOR_ATTACHMENTS."
        val GL_COLOR_ATTACHMENT17 by "The fragment shader output value is written into the nth color attachment of the current framebuffer. n may range from zero to the value of GL_MAX_COLOR_ATTACHMENTS."
        val GL_COLOR_ATTACHMENT18 by "The fragment shader output value is written into the nth color attachment of the current framebuffer. n may range from zero to the value of GL_MAX_COLOR_ATTACHMENTS."
        val GL_COLOR_ATTACHMENT19 by "The fragment shader output value is written into the nth color attachment of the current framebuffer. n may range from zero to the value of GL_MAX_COLOR_ATTACHMENTS."
        val GL_COLOR_ATTACHMENT20 by "The fragment shader output value is written into the nth color attachment of the current framebuffer. n may range from zero to the value of GL_MAX_COLOR_ATTACHMENTS."
        val GL_COLOR_ATTACHMENT21 by "The fragment shader output value is written into the nth color attachment of the current framebuffer. n may range from zero to the value of GL_MAX_COLOR_ATTACHMENTS."
        val GL_COLOR_ATTACHMENT22 by "The fragment shader output value is written into the nth color attachment of the current framebuffer. n may range from zero to the value of GL_MAX_COLOR_ATTACHMENTS."
        val GL_COLOR_ATTACHMENT23 by "The fragment shader output value is written into the nth color attachment of the current framebuffer. n may range from zero to the value of GL_MAX_COLOR_ATTACHMENTS."
        val GL_COLOR_ATTACHMENT24 by "The fragment shader output value is written into the nth color attachment of the current framebuffer. n may range from zero to the value of GL_MAX_COLOR_ATTACHMENTS."
        val GL_COLOR_ATTACHMENT25 by "The fragment shader output value is written into the nth color attachment of the current framebuffer. n may range from zero to the value of GL_MAX_COLOR_ATTACHMENTS."
        val GL_COLOR_ATTACHMENT26 by "The fragment shader output value is written into the nth color attachment of the current framebuffer. n may range from zero to the value of GL_MAX_COLOR_ATTACHMENTS."
        val GL_COLOR_ATTACHMENT27 by "The fragment shader output value is written into the nth color attachment of the current framebuffer. n may range from zero to the value of GL_MAX_COLOR_ATTACHMENTS."
        val GL_COLOR_ATTACHMENT28 by "The fragment shader output value is written into the nth color attachment of the current framebuffer. n may range from zero to the value of GL_MAX_COLOR_ATTACHMENTS."
        val GL_COLOR_ATTACHMENT29 by "The fragment shader output value is written into the nth color attachment of the current framebuffer. n may range from zero to the value of GL_MAX_COLOR_ATTACHMENTS."
        val GL_COLOR_ATTACHMENT30 by "The fragment shader output value is written into the nth color attachment of the current framebuffer. n may range from zero to the value of GL_MAX_COLOR_ATTACHMENTS."
        val GL_COLOR_ATTACHMENT31 by "The fragment shader output value is written into the nth color attachment of the current framebuffer. n may range from zero to the value of GL_MAX_COLOR_ATTACHMENTS."

        noDocumentation
    }

    val GLDrawMode by options {
        val GL_POINTS by noDocumentation
        val GL_LINE_STRIP by noDocumentation
        val GL_LINE_LOOP by noDocumentation
        val GL_LINES by noDocumentation
        val GL_LINE_STRIP_ADJACENCY by noDocumentation
        val GL_LINES_ADJACENCY by noDocumentation
        val GL_TRIANGLE_STRIP by noDocumentation
        val GL_TRIANGLE_FAN by noDocumentation
        val GL_TRIANGLES by noDocumentation
        val GL_TRIANGLE_STRIP_ADJACENCY by noDocumentation
        val GL_TRIANGLES_ADJACENCY by noDocumentation
        val GL_PATCHES by noDocumentation

        noDocumentation
    }

    val GLFramebufferStatus by options {
        val GL_FRAMEBUFFER_COMPLETE by "The  framebuffer is complete."
        val GL_FRAMEBUFFER_UNDEFINED by "The  framebuffer is the default read or draw framebuffer, but the default framebuffer does not exist."
        val GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT by "At least one of the framebuffer attachment points is framebuffer incomplete."
        val GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT by "The framebuffer has no images attached to it."
        val GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER by "The value of GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE is GL_NONE for at least one color attachment point(s) named by GL_DRAW_BUFFERi."
        val GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER by "GL_READ_BUFFER is not GL_NONE and the value of GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE is GL_NONE for the color attachment point named by GL_READ_BUFFER."
        val GL_FRAMEBUFFER_UNSUPPORTED by "The combination of internal formats of the attached images violates an implementation-dependent set of restrictions."
        val GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE by "Returned if the value of GL_RENDERBUFFER_SAMPLES is not the same for all attached renderbuffers; if the value of GL_TEXTURE_SAMPLES is the not same for all attached textures; or, if the attached images are a mix of renderbuffers and textures, the value of GL_RENDERBUFFER_SAMPLES does not match the value of GL_TEXTURE_SAMPLES. Also returned if the value of GL_TEXTURE_FIXED_SAMPLE_LOCATIONS is not the same for all attached textures; or, if the attached images are a mix of renderbuffers and textures, the value of GL_TEXTURE_FIXED_SAMPLE_LOCATIONS is not GL_TRUE for all attached textures."
        val GL_FRAMEBUFFER_INCOMPLETE_LAYER_TARGETS by "A framebuffer attachment is layered, and any populated attachment is not layered, or all populated color attachments are not from textures of the same target."

        noDocumentation
    }

    val GLFramebufferTarget by options {
        val GL_FRAMEBUFFER by noDocumentation
        val GL_READ_FRAMEBUFFER by noDocumentation
        val GL_DRAW_FRAMEBUFFER by noDocumentation

        noDocumentation
    }

    val GLOption by options {
        val GL_BLEND by "If enabled, blend the computed fragment color values with the values in the color buffers. See glBlendFunc."
        val GL_CLIP_DISTANCE0 by "If enabled, clip geometry against user-defined half space 0."
        val GL_CLIP_DISTANCE1 by "If enabled, clip geometry against user-defined half space 1."
        val GL_CLIP_DISTANCE2 by "If enabled, clip geometry against user-defined half space 2."
        val GL_CLIP_DISTANCE3 by "If enabled, clip geometry against user-defined half space 3."
        val GL_CLIP_DISTANCE4 by "If enabled, clip geometry against user-defined half space 4."
        val GL_CLIP_DISTANCE5 by "If enabled, clip geometry against user-defined half space 5."
        val GL_CLIP_DISTANCE6 by "If enabled, clip geometry against user-defined half space 6."
        val GL_CLIP_DISTANCE7 by "If enabled, clip geometry against user-defined half space 7."
        val GL_COLOR_LOGIC_OP by "If enabled, apply the currently selected logical operation to the computed fragment color and color buffer values. See glLogicOp."
        val GL_CULL_FACE by "If enabled, cull polygons based on their winding in window coordinates. See glCullFace."
        val GL_DEBUG_OUTPUT by "If enabled, debug messages are produced by a debug context. When disabled, the debug message log is silenced. Note that in a non-debug context, very few, if any messages might be produced, even when GL_DEBUG_OUTPUT is enabled."
        val GL_DEBUG_OUTPUT_SYNCHRONOUS by "If enabled, debug messages are produced synchronously by a debug context. If disabled, debug messages may be produced asynchronously. In particular, they may be delayed relative to the execution of GL commands, and the debug callback function may be called from a thread other than that in which the commands are executed. See glDebugMessageCallback."
        val GL_DEPTH_CLAMP by "If enabled, the −wc≤zc≤wc plane equation is ignored by view volume clipping (effectively, there is no near or far plane clipping). See glDepthRange."
        val GL_DEPTH_TEST by "If enabled, do depth comparisons and update the depth buffer. Note that even if the depth buffer exists and the depth mask is non-zero, the depth buffer is not updated if the depth test is disabled. See glDepthFunc and glDepthRange."
        val GL_DITHER by "If enabled, dither color components or indices before they are written to the color buffer."
        val GL_FRAMEBUFFER_SRGB by "If enabled and the value of GL_FRAMEBUFFER_ATTACHMENT_COLOR_ENCODING for the framebuffer attachment corresponding to the destination buffer is GL_SRGB, the R, G, and B destination color values (after conversion from fixed-point to floating-point) are considered to be encoded for the sRGB color space and hence are linearized prior to their use in blending."
        val GL_LINE_SMOOTH by "If enabled, draw lines with correct filtering. Otherwise, draw aliased lines. See glLineWidth."
        val GL_MULTISAMPLE by "If enabled, use multiple fragment samples in computing the final color of a pixel. See glSampleCoverage."
        val GL_POLYGON_OFFSET_FILL by "If enabled, and if the polygon is rendered in GL_FILL mode, an offset is added to depth values of a polygon's fragments before the depth comparison is performed. See glPolygonOffset."
        val GL_POLYGON_OFFSET_LINE by "If enabled, and if the polygon is rendered in GL_LINE mode, an offset is added to depth values of a polygon's fragments before the depth comparison is performed. See glPolygonOffset."
        val GL_POLYGON_OFFSET_POINT by "If enabled, an offset is added to depth values of a polygon's fragments before the depth comparison is performed, if the polygon is rendered in GL_POINT mode. See glPolygonOffset."
        val GL_POLYGON_SMOOTH by "If enabled, draw polygons with proper filtering. Otherwise, draw aliased polygons. For correct antialiased polygons, an alpha buffer is needed and the polygons must be sorted front to back."
        val GL_PRIMITIVE_RESTART by "Enables primitive restarting. If enabled, any one of the draw commands which transfers a set of generic attribute array elements to the GL will restart the primitive when the index of the vertex is equal to the primitive restart index. See glPrimitiveRestartIndex."
        val GL_PRIMITIVE_RESTART_FIXED_INDEX by "Enables primitive restarting with a fixed index. If enabled, any one of the draw commands which transfers a set of generic attribute array elements to the GL will restart the primitive when the index of the vertex is equal to the fixed primitive index for the specified index type. The fixed index is equal to 2n−1 where n is equal to 8 for GL_UNSIGNED_BYTE, 16 for GL_UNSIGNED_SHORT and 32 for GL_UNSIGNED_INT."
        val GL_RASTERIZER_DISCARD by "If enabled, primitives are discarded after the optional transform feedback stage, but before rasterization. Furthermore, when enabled, glClear, glClearBufferData, glClearBufferSubData, glClearTexImage, and glClearTexSubImage are ignored."
        val GL_SAMPLE_ALPHA_TO_COVERAGE by "If enabled, compute a temporary coverage value where each bit is determined by the alpha value at the corresponding sample location. The temporary coverage value is then ANDed with the fragment coverage value."
        val GL_SAMPLE_ALPHA_TO_ONE by "If enabled, each sample alpha value is replaced by the maximum representable alpha value."
        val GL_SAMPLE_COVERAGE by "If enabled, the fragment's coverage is ANDed with the temporary coverage value. If GL_SAMPLE_COVERAGE_INVERT is set to GL_TRUE, invert the coverage value. See glSampleCoverage."
        val GL_SAMPLE_SHADING by "If enabled, the active fragment shader is run once for each covered sample, or at fraction of this rate as determined by the current value of GL_MIN_SAMPLE_SHADING_VALUE. See glMinSampleShading."
        val GL_SAMPLE_MASK by "If enabled, the sample coverage mask generated for a fragment during rasterization will be ANDed with the value of GL_SAMPLE_MASK_VALUE before shading occurs. See glSampleMaski."
        val GL_SCISSOR_TEST by "If enabled, discard fragments that are outside the scissor rectangle. See glScissor."
        val GL_STENCIL_TEST by "If enabled, do stencil testing and update the stencil buffer. See glStencilFunc and glStencilOp."
        val GL_TEXTURE_CUBE_MAP_SEAMLESS by "If enabled, cubemap textures are sampled such that when linearly sampling from the border between two adjacent faces, texels from both faces are used to generate the final sample value. When disabled, texels from only a single face are used to construct the final sample value."
        val GL_PROGRAM_POINT_SIZE by "If enabled and a vertex or geometry shader is active, then the derived point size is taken from the (potentially clipped) shader builtin gl_PointSize and clamped to the implementation-dependent point size range."

        noDocumentation
    }

    val GLShaderType by options {
        val GL_FRAGMENT_SHADER by noDocumentation
        val GL_VERTEX_SHADER by noDocumentation
        val GL_GEOMETRY_SHADER by noDocumentation
        val GL_TESS_CONTROL_SHADER by noDocumentation
        val GL_TESS_EVALUATION_SHADER by noDocumentation
        val GL_COMPUTE_SHADER by noDocumentation

        noDocumentation
    }

    val GLStencilAction by options {
        val GL_KEEP by "The currently stored stencil value is kept."
        val GL_ZERO by "The stencil value is set to 0."
        val GL_REPLACE by "The stencil value is replaced with the reference value set with glStencilFunc."
        val GL_INCR by "The stencil value is increased by 1 if it is lower than the maximum value."
        val GL_INCR_WRAP by "Same as GL_INCR, but wraps it back to 0 as soon as the maximum value is exceeded."
        val GL_DECR by "The stencil value is decreased by 1 if it is higher than the minimum value."
        val GL_DECR_WRAP by "Same as GL_DECR, but wraps it to the maximum value if it ends up lower than 0."
        val GL_INVERT by "Bitwise inverts the current stencil buffer value."

        noDocumentation
    }

    val GLStencilFunction by options {
        val GL_ALWAYS by "The stencil test always passes."
        val GL_NEVER by "The stencil test never passes."
        val GL_LESS by "Passes if the fragment's stencil value is less than the reference stencil value."
        val GL_EQUAL by "Passes if the fragment's stencil value is equal to the reference stencil value."
        val GL_LEQUAL by "Passes if the fragment's stencil value is less than or equal to the reference stencil value."
        val GL_GREATER by "Passes if the fragment's stencil value is greater than the reference stencil value."
        val GL_NOTEQUAL by "Passes if the fragment's stencil value is not equal to the reference stencil value."
        val GL_GEQUAL by "Passes if the fragment's stencil value is greater than or equal to the reference stencil value."

        noDocumentation
    }

    val GLTextureImageFormat by options {
        val GL_RED by noDocumentation
        val GL_RG by noDocumentation
        val GL_RGB by noDocumentation
        val GL_BGR by noDocumentation
        val GL_RGBA by noDocumentation
        val GL_BGRA by noDocumentation
        val GL_RED_INTEGER by noDocumentation
        val GL_RG_INTEGER by noDocumentation
        val GL_RGB_INTEGER by noDocumentation
        val GL_BGR_INTEGER by noDocumentation
        val GL_RGBA_INTEGER by noDocumentation
        val GL_BGRA_INTEGER by noDocumentation
        val GL_STENCIL_INDEX by noDocumentation
        val GL_DEPTH_COMPONENT by noDocumentation
        val GL_DEPTH_STENCIL by noDocumentation

        "Specifies the format of the pixel data."
    }

    val GLTextureImageInternalFormat by options {
        val GL_DEPTH_COMPONENT by noDocumentation
        val GL_DEPTH_STENCIL by noDocumentation
        val GL_RED by noDocumentation
        val GL_RG by noDocumentation
        val GL_RGB by noDocumentation
        val GL_RGBA by noDocumentation

        // TODO: these might not be supported for glTexImage2D idk
        val GL_DEPTH_COMPONENT16 by noDocumentation
        val GL_DEPTH_COMPONENT24 by noDocumentation
        val GL_DEPTH_COMPONENT32 by noDocumentation
        val GL_DEPTH_COMPONENT32F by noDocumentation

        val GL_R8 by noDocumentation
        val GL_R8_SNORM by noDocumentation
        val GL_R16 by noDocumentation
        val GL_R16_SNORM by noDocumentation
        val GL_RG8 by noDocumentation
        val GL_RG8_SNORM by noDocumentation
        val GL_RG16 by noDocumentation
        val GL_RG16_SNORM by noDocumentation
        val GL_R3_G3_B2 by noDocumentation
        val GL_RGB4 by noDocumentation
        val GL_RGB5 by noDocumentation
        val GL_RGB8 by noDocumentation
        val GL_RGB8_SNORM by noDocumentation
        val GL_RGB10 by noDocumentation
        val GL_RGB12 by noDocumentation
        val GL_RGB16_SNORM by noDocumentation
        val GL_RGBA2 by noDocumentation
        val GL_RGBA4 by noDocumentation
        val GL_RGB5_A1 by noDocumentation
        val GL_RGBA8 by noDocumentation
        val GL_RGBA8_SNORM by noDocumentation
        val GL_RGB10_A2 by noDocumentation
        val GL_RGB10_A2UI by noDocumentation
        val GL_RGBA12 by noDocumentation
        val GL_RGBA16 by noDocumentation
        val GL_SRGB8 by noDocumentation
        val GL_SRGB8_ALPHA8 by noDocumentation
        val GL_R16F by noDocumentation
        val GL_RG16F by noDocumentation
        val GL_RGB16F by noDocumentation
        val GL_RGBA16F by noDocumentation
        val GL_R32F by noDocumentation
        val GL_RG32F by noDocumentation
        val GL_RGB32F by noDocumentation
        val GL_RGBA32F by noDocumentation
        val GL_R11F_G11F_B10F by noDocumentation
        val GL_RGB9_E5 by noDocumentation
        val GL_R8I by noDocumentation
        val GL_R8UI by noDocumentation
        val GL_R16I by noDocumentation
        val GL_R16UI by noDocumentation
        val GL_R32I by noDocumentation
        val GL_R32UI by noDocumentation
        val GL_RG8I by noDocumentation
        val GL_RG8UI by noDocumentation
        val GL_RG16I by noDocumentation
        val GL_RG16UI by noDocumentation
        val GL_RG32I by noDocumentation
        val GL_RG32UI by noDocumentation
        val GL_RGB8I by noDocumentation
        val GL_RGB8UI by noDocumentation
        val GL_RGB16I by noDocumentation
        val GL_RGB16UI by noDocumentation
        val GL_RGB32I by noDocumentation
        val GL_RGB32UI by noDocumentation
        val GL_RGBA8I by noDocumentation
        val GL_RGBA8UI by noDocumentation
        val GL_RGBA16I by noDocumentation
        val GL_RGBA16UI by noDocumentation
        val GL_RGBA32I by noDocumentation
        val GL_RGBA32UI by noDocumentation
        val GL_COMPRESSED_RED by noDocumentation
        val GL_COMPRESSED_RG by noDocumentation
        val GL_COMPRESSED_RGB by noDocumentation
        val GL_COMPRESSED_RGBA by noDocumentation
        val GL_COMPRESSED_SRGB by noDocumentation
        val GL_COMPRESSED_SRGB_ALPHA by noDocumentation
        val GL_COMPRESSED_RED_RGTC1 by noDocumentation
        val GL_COMPRESSED_SIGNED_RED_RGTC1 by noDocumentation
        val GL_COMPRESSED_RG_RGTC2 by noDocumentation
        val GL_COMPRESSED_SIGNED_RG_RGTC2 by noDocumentation
        val GL_COMPRESSED_RGBA_BPTC_UNORM by noDocumentation
        val GL_COMPRESSED_SRGB_ALPHA_BPTC_UNORM by noDocumentation
        val GL_COMPRESSED_RGB_BPTC_SIGNED_FLOAT by noDocumentation
        val GL_COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT by noDocumentation

        "If an application wants to store the texture at a certain resolution or in a certain format, it can request the resolution and format with internalformat. The GL will choose an internal representation that closely approximates that requested by internalformat, but it may not match exactly."
    }

    val GLTextureImageType by options {
        val GL_UNSIGNED_BYTE by noDocumentation
        val GL_BYTE by noDocumentation
        val GL_UNSIGNED_SHORT by noDocumentation
        val GL_SHORT by noDocumentation
        val GL_UNSIGNED_INT by noDocumentation
        val GL_INT by noDocumentation
        val GL_HALF_FLOAT by noDocumentation
        val GL_FLOAT by noDocumentation
        val GL_UNSIGNED_BYTE_3_3_2 by noDocumentation
        val GL_UNSIGNED_BYTE_2_3_3_REV by noDocumentation
        val GL_UNSIGNED_SHORT_5_6_5 by noDocumentation
        val GL_UNSIGNED_SHORT_5_6_5_REV by noDocumentation
        val GL_UNSIGNED_SHORT_4_4_4_4 by noDocumentation
        val GL_UNSIGNED_SHORT_4_4_4_4_REV by noDocumentation
        val GL_UNSIGNED_SHORT_5_5_5_1 by noDocumentation
        val GL_UNSIGNED_SHORT_1_5_5_5_REV by noDocumentation
        val GL_UNSIGNED_INT_8_8_8_8 by noDocumentation
        val GL_UNSIGNED_INT_8_8_8_8_REV by noDocumentation
        val GL_UNSIGNED_INT_10_10_10_2 by noDocumentation
        val GL_UNSIGNED_INT_2_10_10_10_REV by noDocumentation

        noDocumentation
    }

    val GLTextureParameter by options {
        val GL_DEPTH_STENCIL_TEXTURE_MODE by noDocumentation
        val GL_TEXTURE_BASE_LEVEL by noDocumentation
        val GL_TEXTURE_COMPARE_FUNC by noDocumentation
        val GL_TEXTURE_COMPARE_MODE by noDocumentation
        val GL_TEXTURE_LOD_BIAS by noDocumentation
        val GL_TEXTURE_MIN_FILTER by noDocumentation
        val GL_TEXTURE_MAG_FILTER by noDocumentation
        val GL_TEXTURE_MIN_LOD by noDocumentation
        val GL_TEXTURE_MAX_LOD by noDocumentation
        val GL_TEXTURE_MAX_LEVEL by noDocumentation
        val GL_TEXTURE_SWIZZLE_R by noDocumentation
        val GL_TEXTURE_SWIZZLE_G by noDocumentation
        val GL_TEXTURE_SWIZZLE_B by noDocumentation
        val GL_TEXTURE_SWIZZLE_A by noDocumentation
        val GL_TEXTURE_WRAP_S by noDocumentation
        val GL_TEXTURE_WRAP_T by noDocumentation
        val GL_TEXTURE_WRAP_R by noDocumentation
        val GL_TEXTURE_BORDER_COLOR by noDocumentation
        val GL_TEXTURE_SWIZZLE_RGBA by noDocumentation

        noDocumentation
    }

    val GLTextureParameterI by options {
        val GL_DEPTH_STENCIL_TEXTURE_MODE by noDocumentation
        val GL_TEXTURE_BASE_LEVEL by noDocumentation
        val GL_TEXTURE_COMPARE_FUNC by noDocumentation
        val GL_TEXTURE_COMPARE_MODE by noDocumentation
        val GL_TEXTURE_LOD_BIAS by noDocumentation
        val GL_TEXTURE_MIN_FILTER by noDocumentation
        val GL_TEXTURE_MAG_FILTER by noDocumentation
        val GL_TEXTURE_MIN_LOD by noDocumentation
        val GL_TEXTURE_MAX_LOD by noDocumentation
        val GL_TEXTURE_MAX_LEVEL by noDocumentation
        val GL_TEXTURE_SWIZZLE_R by noDocumentation
        val GL_TEXTURE_SWIZZLE_G by noDocumentation
        val GL_TEXTURE_SWIZZLE_B by noDocumentation
        val GL_TEXTURE_SWIZZLE_A by noDocumentation
        val GL_TEXTURE_WRAP_S by noDocumentation
        val GL_TEXTURE_WRAP_T by noDocumentation
        val GL_TEXTURE_WRAP_R by noDocumentation

        noDocumentation
    }

    val GLTextureParameterMagFilterValue by options {
        val GL_NEAREST by "Returns the value of the texture element that is nearest (in Manhattan distance) to the specified texture coordinates."
        val GL_LINEAR by "Returns the weighted average of the texture elements that are closest to the specified texture coordinates. These can include items wrapped or repeated from other parts of a texture, depending on the values of GL_TEXTURE_WRAP_S and GL_TEXTURE_WRAP_T, and on the exact mapping."

        noDocumentation
    }

    val GLTextureParameterMinFilterValue by options {
        val GL_NEAREST by "Returns the value of the texture element that is nearest (in Manhattan distance) to the specified texture coordinates."
        val GL_LINEAR by "Returns the weighted average of the four texture elements that are closest to the specified texture coordinates. These can include items wrapped or repeated from other parts of a texture, depending on the values of GL_TEXTURE_WRAP_S and GL_TEXTURE_WRAP_T, and on the exact mapping."
        val GL_NEAREST_MIPMAP_NEAREST by "Chooses the mipmap that most closely matches the size of the pixel being textured and uses the GL_NEAREST criterion (the texture element closest to the specified texture coordinates) to produce a texture value."
        val GL_LINEAR_MIPMAP_NEAREST by "Chooses the mipmap that most closely matches the size of the pixel being textured and uses the GL_LINEAR criterion (a weighted average of the four texture elements that are closest to the specified texture coordinates) to produce a texture value."
        val GL_NEAREST_MIPMAP_LINEAR by "Chooses the two mipmaps that most closely match the size of the pixel being textured and uses the GL_NEAREST criterion (the texture element closest to the specified texture coordinates ) to produce a texture value from each mipmap. The final texture value is a weighted average of those two values."
        val GL_LINEAR_MIPMAP_LINEAR by "Chooses the two mipmaps that most closely match the size of the pixel being textured and uses the GL_LINEAR criterion (a weighted average of the texture elements that are closest to the specified texture coordinates) to produce a texture value from each mipmap. The final texture value is a weighted average of those two values."

        noDocumentation
    }

    val GLTextureParameterSwizzle by options {
        val GL_TEXTURE_SWIZZLE_R by noDocumentation
        val GL_TEXTURE_SWIZZLE_G by noDocumentation
        val GL_TEXTURE_SWIZZLE_B by noDocumentation
        val GL_TEXTURE_SWIZZLE_A by noDocumentation

        noDocumentation
    }

    val GLTextureParameterSwizzleValue by options {
        val GL_RED by noDocumentation
        val GL_GREEN by noDocumentation
        val GL_BLUE by noDocumentation
        val GL_ALPHA by noDocumentation
        val GL_ONE by noDocumentation
        val GL_ZERO by noDocumentation

        noDocumentation
    }

    val GLTextureParameterWrap by options {
        val GL_TEXTURE_WRAP_S by noDocumentation
        val GL_TEXTURE_WRAP_T by noDocumentation
        val GL_TEXTURE_WRAP_R by noDocumentation

        noDocumentation
    }

    val GLTextureParameterWrapValue by options {
        val GL_CLAMP_TO_EDGE by noDocumentation
        val GL_CLAMP_TO_BORDER by noDocumentation
        val GL_MIRRORED_REPEAT by noDocumentation
        val GL_REPEAT by noDocumentation
        val GL_MIRROR_CLAMP_TO_EDGE by noDocumentation

        noDocumentation
    }

    val GLTextureTarget by options {
        val GL_TEXTURE_1D by noDocumentation
        val GL_TEXTURE_1D_ARRAY by noDocumentation
        val GL_TEXTURE_2D by noDocumentation
        val GL_TEXTURE_2D_ARRAY by noDocumentation
        val GL_TEXTURE_2D_MULTISAMPLE by noDocumentation
        val GL_TEXTURE_2D_MULTISAMPLE_ARRAY by noDocumentation
        val GL_TEXTURE_3D by noDocumentation
        val GL_TEXTURE_CUBE_MAP by noDocumentation
        val GL_TEXTURE_CUBE_MAP_ARRAY by noDocumentation
        val GL_TEXTURE_RECTANGLE by noDocumentation

        noDocumentation
    }

    val GLType by options {
        val GL_BYTE by noDocumentation
        val GL_UNSIGNED_BYTE by noDocumentation
        val GL_SHORT by noDocumentation
        val GL_UNSIGNED_SHORT by noDocumentation
        val GL_INT by noDocumentation
        val GL_UNSIGNED_INT by noDocumentation
        val GL_HALF_FLOAT by noDocumentation
        val GL_FLOAT by noDocumentation
        val GL_DOUBLE by noDocumentation
        val GL_FIXED by noDocumentation
        val GL_INT_2_10_10_10_REV by noDocumentation
        val GL_UNSIGNED_INT_2_10_10_10_REV by noDocumentation
        val GL_UNSIGNED_INT_10F_11F_11F_REV by noDocumentation

        noDocumentation
    }

    val GLWindingOrder by options {
        val GL_CW by "Clockwise."
        val GL_CCW by "Anti-clockwise."

        noDocumentation
    }
}

/** TODO */
fun buildGLenumFileContent(): String {
    val fileContent = FileSpec.builder("me.exerro.eggli.enum", "GLenum")
    val reverseLookup = mutableMapOf<GLenumDescriptor.Member, MutableList<String>>()

    for (e in enumData) {
        for (m in e.members) {
            reverseLookup.computeIfAbsent(m) { mutableListOf() } .add(e.name)
        }
    }

    for (e in enumData) {
        val cb = TypeSpec
            .companionObjectBuilder()
            .addKdoc("@see ${e.name}")
            .addFunction(FunSpec
                .builder("fromGLValue")
                .addKdoc("Convert a raw integral value from OpenGL to an instance of [${e.name}].\n")
                .addKdoc("@throws IllegalStateException when [glValue] is not a valid value for [${e.name}]\n")
                .addKdoc("@see ${e.name}")
                .addParameter("glValue", Int::class)
                .beginControlFlow("return·when·(glValue)")
                .also { builder ->
                    for (m in e.members) {
                        builder.addStatement("org.lwjgl.opengl.GL46C.${m.name}·->·me.exerro.eggli.enum.${m.name}")
                    }
                }
                .addStatement("else·->·error(%S.format(glValue))", "%d is not a valid value of ${e.name}")
                .endControlFlow()
                .build())

        for (m in e.members) {
            cb.addProperty(PropertySpec
                .builder(m.kotlinName, ClassName("me.exerro.eggli.enum", e.name))
                .addKdoc(m.docString)
                .addKdoc("\n@see me.exerro.eggli.enum." + m.name)
                .initializer("me.exerro.eggli.enum." + m.name)
                .build()
            )
        }

        val ib = TypeSpec
            .interfaceBuilder(e.name)
            .addKdoc(e.docString)
            .addModifiers(KModifier.SEALED)
            .addProperty(PropertySpec
                .builder("glName", String::class)
                .addKdoc("OpenGL name of this [${e.name}].")
                .build())
            .addProperty(PropertySpec
                .builder("glValue", Int::class)
                .addKdoc("OpenGL facing integer value of this [${e.name}].")
                .build())
            .addType(cb.build())
            .also { builder ->
                for (m in e.members) {
                    builder.addKdoc("\n@see ${m.kotlinName}")
                }
            }

        fileContent.addType(ib.build())
    }

    for ((m, es) in reverseLookup) {
        val pb = TypeSpec
            .objectBuilder(m.name)
            .addProperty(PropertySpec
                .builder("glName", String::class, KModifier.OVERRIDE)
                .initializer("\"${m.name}\"")
                .build())
            .addProperty(PropertySpec
                .builder("glValue", Int::class, KModifier.OVERRIDE)
                .initializer("org.lwjgl.opengl.GL46C.${m.name}")
                .build())
            .addFunction(FunSpec
                .builder("toString")
                .addModifiers(KModifier.OVERRIDE)
                .addCode("return \"${m.name}\"")
                .build())
            .addFunction(FunSpec
                .builder("hashCode")
                .addModifiers(KModifier.OVERRIDE)
                .addCode("return glValue")
                .build())
            .addFunction(FunSpec
                .builder("equals")
                .addParameter("other", Any::class.asTypeName().copy(nullable = true))
                .addModifiers(KModifier.OVERRIDE)
                .addCode("return other·is·${m.name}·||·glValue·==·other")
                .build())

        for (e in es) pb
            .addKdoc("\n@see $e.${m.kotlinName}")
            .addSuperinterface(ClassName("me.exerro.eggli.enum", e))

        fileContent.addType(pb.build())
    }

    return fileContent.build().toString()
}

private fun createEnumData(
    block: context (GLenumDataContext) () -> Unit
): List<GLenumDescriptor> {
    val descriptors = mutableListOf<GLenumDescriptor>()

    with(object: GLenumDataContext {
        override val noDocumentation = "TO" + "DO" // this is correct!

        override fun options(block: context(GLenumDescriptorContext) () -> String): GLenumDescriptor {
            val members = mutableListOf<GLenumDescriptor.Member>()
            val docString = with(object: GLenumDescriptorContext {
                override fun String.provideDelegate(thisRef: Any?, property: KProperty<*>): UnitGetterDelegate {
                    members.add(GLenumDescriptor.Member(name = property.name, docString = this))
                    return UnitGetterDelegate
                }
            }, block)

            return GLenumDescriptor(name = "", docString = docString, members = members)
        }

        override fun GLenumDescriptor.provideDelegate(thisRef: Any?, property: KProperty<*>): UnitGetterDelegate {
            descriptors.add(copy(name = property.name))
            return UnitGetterDelegate
        }
    }, block)

    return descriptors
}

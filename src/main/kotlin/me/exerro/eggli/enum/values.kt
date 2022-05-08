package me.exerro.eggli.enum

import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.GLBufferTarget.*
import me.exerro.eggli.enum.GLBufferUsage.*
import me.exerro.eggli.enum.GLDrawMode.*
import me.exerro.eggli.enum.GLClearBit.Companion.ColorBuffer
import me.exerro.eggli.enum.GLClearBit.Companion.DepthBuffer
import me.exerro.eggli.enum.GLClearBit.Companion.StencilBuffer
import me.exerro.eggli.enum.GLShaderType.*
import me.exerro.eggli.enum.GLOption.*
import me.exerro.eggli.enum.GLType.*
import kotlin.Array

/** @see Array */
context (GLContext)
inline val GL_ARRAY_BUFFER get() = Array

/** @see AtomicCounter */
context (GLContext)
inline val GL_ATOMIC_COUNTER_BUFFER get() = AtomicCounter

/** @see CopyRead */
context (GLContext)
inline val GL_COPY_READ_BUFFER get() = CopyRead

/** @see CopyWrite */
context (GLContext)
inline val GL_COPY_WRITE_BUFFER get() = CopyWrite

/** @see DrawIndirect */
context (GLContext)
inline val GL_DRAW_INDIRECT_BUFFER get() = DrawIndirect

/** @see DispatchIndirect */
context (GLContext)
inline val GL_DISPATCH_INDIRECT_BUFFER get() = DispatchIndirect

/** @see ElementArray */
context (GLContext)
inline val GL_ELEMENT_ARRAY_BUFFER get() = ElementArray

/** @see PixelPack */
context (GLContext)
inline val GL_PIXEL_PACK_BUFFER get() = PixelPack

/** @see PixelUnpack */
context (GLContext)
inline val GL_PIXEL_UNPACK_BUFFER get() = PixelUnpack

/** @see Query */
context (GLContext)
inline val GL_QUERY_BUFFER get() = Query

/** @see ShaderStorage */
context (GLContext)
inline val GL_SHADER_STORAGE_BUFFER get() = ShaderStorage

/** @see Texture */
context (GLContext)
inline val GL_TEXTURE_BUFFER get() = Texture

/** @see TransformFeedback */
context (GLContext)
inline val GL_TRANSFORM_FEEDBACK_BUFFER get() = TransformFeedback

/** @see Uniform */
context (GLContext)
inline val GL_UNIFORM_BUFFER get() = Uniform

////////////////////////////////////////////////////////////////

/** @see StreamDraw */
context (GLContext)
inline val GL_STREAM_DRAW get() = StreamDraw

/** @see StreamRead */
context (GLContext)
inline val GL_STREAM_READ get() = StreamRead

/** @see StreamCopy */
context (GLContext)
inline val GL_STREAM_COPY get() = StreamCopy

/** @see StaticDraw */
context (GLContext)
inline val GL_STATIC_DRAW get() = StaticDraw

/** @see StaticRead */
context (GLContext)
inline val GL_STATIC_READ get() = StaticRead

/** @see StaticCopy */
context (GLContext)
inline val GL_STATIC_COPY get() = StaticCopy

/** @see DynamicDraw */
context (GLContext)
inline val GL_DYNAMIC_DRAW get() = DynamicDraw

/** @see DynamicRead */
context (GLContext)
inline val GL_DYNAMIC_READ get() = DynamicRead

/** @see DynamicCopy */
context (GLContext)
inline val GL_DYNAMIC_COPY get() = DynamicCopy

////////////////////////////////////////////////////////////////

/** @see Points */
context (GLContext)
inline val GL_POINTS get() = Points

/** @see LineStrip */
context (GLContext)
inline val GL_LINE_STRIP get() = LineStrip

/** @see LineLoop */
context (GLContext)
inline val GL_LINE_LOOP get() = LineLoop

/** @see Lines */
context (GLContext)
inline val GL_LINES get() = Lines

/** @see LineStripAdjacency */
context (GLContext)
inline val GL_LINE_STRIP_ADJACENCY get() = LineStripAdjacency

/** @see LinesAdjacency */
context (GLContext)
inline val GL_LINES_ADJACENCY get() = LinesAdjacency

/** @see TriangleStrip */
context (GLContext)
inline val GL_TRIANGLE_STRIP get() = TriangleStrip

/** @see TriangleFan */
context (GLContext)
inline val GL_TRIANGLE_FAN get() = TriangleFan

/** @see Triangles */
context (GLContext)
inline val GL_TRIANGLES get() = Triangles

/** @see TriangleStripAdjacency */
context (GLContext)
inline val GL_TRIANGLE_STRIP_ADJACENCY get() = TriangleStripAdjacency

/** @see TrianglesAdjacency */
context (GLContext)
inline val GL_TRIANGLES_ADJACENCY get() = TrianglesAdjacency

/** @see Patches */
context (GLContext)
inline val GL_PATCHES get() = Patches

////////////////////////////////////////////////////////////////

/** @see ColorBuffer */
context (GLContext)
inline val GL_COLOR_BUFFER_BIT get() = ColorBuffer

/** @see DepthBuffer */
context (GLContext)
inline val GL_DEPTH_BUFFER_BIT get() = DepthBuffer

/** @see StencilBuffer */
context (GLContext)
inline val GL_STENCIL_BUFFER_BIT get() = StencilBuffer

////////////////////////////////////////////////////////////////

/** @see Fragment */
context (GLContext)
inline val GL_FRAGMENT_SHADER get() = Fragment

/** @see Vertex */
context (GLContext)
inline val GL_VERTEX_SHADER get() = Vertex

/** @see Geometry */
context (GLContext)
inline val GL_GEOMETRY_SHADER get() = Geometry

/** @see TesselationControl */
context (GLContext)
inline val GL_TESS_CONTROL_SHADER get() = TesselationControl

/** @see TesselationEvaluation */
context (GLContext)
inline val GL_TESS_EVALUATION_SHADER get() = TesselationEvaluation

/** @see Compute */
context (GLContext)
inline val GL_COMPUTE_SHADER get() = Compute

////////////////////////////////////////////////////////////////

/** @see Blend */
context (GLContext)
inline val GL_BLEND get() = Blend

/** @see ClipDistance0 */
context (GLContext)
inline val GL_CLIP_DISTANCE0 get() = ClipDistance0

/** @see ClipDistance1 */
context (GLContext)
inline val GL_CLIP_DISTANCE1 get() = ClipDistance1

/** @see ClipDistance2 */
context (GLContext)
inline val GL_CLIP_DISTANCE2 get() = ClipDistance2

/** @see ClipDistance3 */
context (GLContext)
inline val GL_CLIP_DISTANCE3 get() = ClipDistance3

/** @see ClipDistance4 */
context (GLContext)
inline val GL_CLIP_DISTANCE4 get() = ClipDistance4

/** @see ClipDistance5 */
context (GLContext)
inline val GL_CLIP_DISTANCE5 get() = ClipDistance5

/** @see ClipDistance6 */
context (GLContext)
inline val GL_CLIP_DISTANCE6 get() = ClipDistance6

/** @see ClipDistance7 */
context (GLContext)
inline val GL_CLIP_DISTANCE7 get() = ClipDistance7

/** @see ColorLogicOp */
context (GLContext)
inline val GL_COLOR_LOGIC_OP get() = ColorLogicOp

/** @see CullFace */
context (GLContext)
inline val GL_CULL_FACE get() = CullFace

/** @see DebugOutput */
context (GLContext)
inline val GL_DEBUG_OUTPUT get() = DebugOutput

/** @see DebugOutputSynchronous */
context (GLContext)
inline val GL_DEBUG_OUTPUT_SYNCHRONOUS get() = DebugOutputSynchronous

/** @see DepthClamp */
context (GLContext)
inline val GL_DEPTH_CLAMP get() = DepthClamp

/** @see DepthTest */
context (GLContext)
inline val GL_DEPTH_TEST get() = DepthTest

/** @see Dither */
context (GLContext)
inline val GL_DITHER get() = Dither

/** @see FramebufferSRGB */
context (GLContext)
inline val GL_FRAMEBUFFER_SRGB get() = FramebufferSRGB

/** @see LineSmooth */
context (GLContext)
inline val GL_LINE_SMOOTH get() = LineSmooth

/** @see Multisample */
context (GLContext)
inline val GL_MULTISAMPLE get() = Multisample

/** @see PolygonOffsetFill */
context (GLContext)
inline val GL_POLYGON_OFFSET_FILL get() = PolygonOffsetFill

/** @see PolygonOffsetLine */
context (GLContext)
inline val GL_POLYGON_OFFSET_LINE get() = PolygonOffsetLine

/** @see PolygonOffsetPoint */
context (GLContext)
inline val GL_POLYGON_OFFSET_POINT get() = PolygonOffsetPoint

/** @see PolygonSmooth */
context (GLContext)
inline val GL_POLYGON_SMOOTH get() = PolygonSmooth

/** @see PrimitiveRestart */
context (GLContext)
inline val GL_PRIMITIVE_RESTART get() = PrimitiveRestart

/** @see PrimitiveRestartFixedIndex */
context (GLContext)
inline val GL_PRIMITIVE_RESTART_FIXED_INDEX get() = PrimitiveRestartFixedIndex

/** @see RasterizerDiscard */
context (GLContext)
inline val GL_RASTERIZER_DISCARD get() = RasterizerDiscard

/** @see SampleAlphaToCoverage */
context (GLContext)
inline val GL_SAMPLE_ALPHA_TO_COVERAGE get() = SampleAlphaToCoverage

/** @see SampleAlphaToOne */
context (GLContext)
inline val GL_SAMPLE_ALPHA_TO_ONE get() = SampleAlphaToOne

/** @see SampleCoverage */
context (GLContext)
inline val GL_SAMPLE_COVERAGE get() = SampleCoverage

/** @see SampleShading */
context (GLContext)
inline val GL_SAMPLE_SHADING get() = SampleShading

/** @see SampleMask */
context (GLContext)
inline val GL_SAMPLE_MASK get() = SampleMask

/** @see ScissorTest */
context (GLContext)
inline val GL_SCISSOR_TEST get() = ScissorTest

/** @see StencilTest */
context (GLContext)
inline val GL_STENCIL_TEST get() = StencilTest

/** @see TextureCubeMapSeamless */
context (GLContext)
inline val GL_TEXTURE_CUBE_MAP_SEAMLESS get() = TextureCubeMapSeamless

/** @see ProgramPointSize */
context (GLContext)
inline val GL_PROGRAM_POINT_SIZE get() = ProgramPointSize

////////////////////////////////////////////////////////////////

/** @see Byte */
context (GLContext)
inline val GL_BYTE get() = GLType.Byte

/** @see UnsignedByte */
context (GLContext)
inline val GL_UNSIGNED_BYTE get() = UnsignedByte

/** @see Short */
context (GLContext)
inline val GL_SHORT get() = GLType.Short

/** @see UnsignedShort */
context (GLContext)
inline val GL_UNSIGNED_SHORT get() = UnsignedShort

/** @see Int */
context (GLContext)
inline val GL_INT get() = GLType.Int

/** @see UnsignedInt */
context (GLContext)
inline val GL_UNSIGNED_INT get() = UnsignedInt

/** @see HalfFloat */
context (GLContext)
inline val GL_HALF_FLOAT get() = HalfFloat

/** @see Float */
context (GLContext)
inline val GL_FLOAT get() = GLType.Float

/** @see Double */
context (GLContext)
inline val GL_DOUBLE get() = GLType.Double

/** @see Fixed */
context (GLContext)
inline val GL_FIXED get() = Fixed

/** @see Int2101010Rev */
context (GLContext)
inline val GL_INT_2_10_10_10_REV get() = Int2101010Rev

/** @see UnsignedInt2101010Rev */
context (GLContext)
inline val GL_UNSIGNED_INT_2_10_10_10_REV get() = UnsignedInt2101010Rev

/** @see UnsignedInt10f11f11fRev */
context (GLContext)
inline val GL_UNSIGNED_INT_10F_11F_11F_REV get() = UnsignedInt10f11f11fRev

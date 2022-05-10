package me.exerro.eggli.enum

import me.exerro.eggli.enum.GLClearBit.Companion.ColorBuffer
import me.exerro.eggli.enum.GLClearBit.Companion.DepthBuffer
import me.exerro.eggli.enum.GLClearBit.Companion.StencilBuffer

/** @see ColorBuffer */
inline val GL_COLOR_BUFFER_BIT get() = ColorBuffer

/** @see DepthBuffer */
inline val GL_DEPTH_BUFFER_BIT get() = DepthBuffer

/** @see StencilBuffer */
inline val GL_STENCIL_BUFFER_BIT get() = StencilBuffer

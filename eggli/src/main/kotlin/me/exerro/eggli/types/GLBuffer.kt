package me.exerro.eggli.types

import me.exerro.eggli.GLResource
import me.exerro.eggli.gl.glCreateBuffers

/**
 * An OpenGL buffer name.
 * @see glCreateBuffers
 */
typealias GLBuffer = GLResource<Int>

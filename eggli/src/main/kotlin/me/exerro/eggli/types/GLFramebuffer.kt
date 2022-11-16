package me.exerro.eggli.types

import me.exerro.eggli.GLResource
import me.exerro.eggli.gl.glGenFramebuffers

/**
 * An OpenGL framebuffer name.
 * @see glGenFramebuffers
 */
typealias GLFramebuffer = GLResource<Int>

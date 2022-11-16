package me.exerro.eggli.types

import me.exerro.eggli.GLResource
import me.exerro.eggli.gl.glCreateProgram
import me.exerro.egglix.shader.createShaderProgram

/**
 * An OpenGL shader program name.
 * @see glCreateProgram
 * @see GLShader
 * @see createShaderProgram
 */
typealias GLShaderProgram = GLResource<Int>

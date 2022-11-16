package me.exerro.eggli.types

import me.exerro.eggli.GLResource
import me.exerro.eggli.gl.glCreateShader
import me.exerro.egglix.shader.createShaderProgram

/**
 * An OpenGL shader name.
 * @see glCreateShader
 * @see GLShaderProgram
 * @see createShaderProgram
 */
typealias GLShader = GLResource<Int>

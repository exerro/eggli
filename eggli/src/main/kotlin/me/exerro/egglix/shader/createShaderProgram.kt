package me.exerro.egglix.shader

import me.exerro.eggli.GL
import me.exerro.eggli.enum.GLShaderType
import me.exerro.eggli.enum.GL_FRAGMENT_SHADER
import me.exerro.eggli.enum.GL_VERTEX_SHADER
import me.exerro.eggli.gl.*
import me.exerro.lifetimes.Lifetime
import me.exerro.lifetimes.withLifetime

/** TODO */
context (Lifetime)
fun createShaderProgram(
    vertex: String,
    fragment: String,
    label: String? = null,
) = createShaderProgram(
    GL_VERTEX_SHADER to vertex,
    GL_FRAGMENT_SHADER to fragment,
    label = label,
)

/** TODO */
context (Lifetime)
fun createShaderProgram(
    vararg shaders: Pair<GLShaderType, String>,
    label: String? = null,
) = createShaderProgram(shaders.toList(), label = label)

/** @see createShaderProgram */
context (Lifetime)
fun createShaderProgram(
    shaders: Iterable<Pair<GLShaderType, String>>,
    label: String? = null,
) = GL {
    val (shaderProgram) = glCreateProgram(label)

    // TODO: if the lifetime ends within this function, things will get weird
    //  should wrap in a lifetime.ifAlive kinda thing

    // we wrap this code in a lifetime so that any allocations (i.e. the
    // individual shaders) are cleaned up automatically afterwards
    withLifetime {
        for ((type, source) in shaders) {
            val (s) = glCreateShader(type)
            glShaderSource(s, source)
            glCompileShader(s)
            glAttachShader(shaderProgram, s)
        }

        glLinkProgram(shaderProgram)

        // here, shaders will be destroyed, and they're not necessary because
        // the shader program has already been linked
    }

    shaderProgram
}

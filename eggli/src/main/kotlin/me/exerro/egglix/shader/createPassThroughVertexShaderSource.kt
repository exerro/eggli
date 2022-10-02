package me.exerro.egglix.shader

import me.exerro.eggli.types.GLAttributeIndex

/** TODO */
fun createPassThroughVertexShaderSource(
    location: GLAttributeIndex = 0,
    extraAttributes: Iterable<ShaderAttribute>,
) = PASS_THROUGH_VERTEX_SHADER_TEMPLATE
    .replace("%POSITION_LOCATION%", location.toString())
    .replace("%EXTRA_IN%", extraAttributes.joinToString("\n") { "layout (location = ${it.index}) in ${it.type} v_${it.name};" })
    .replace("%EXTRA_OUT%", extraAttributes.joinToString("\n") { "out ${it.type} f_${it.name};" })
    .replace("%PASS_THROUGH%", extraAttributes.joinToString("\n\t") { "f_${it.name} = v_${it.name};" })

/** Vararg version of [createPassThroughVertexShaderSource]. */
fun createPassThroughVertexShaderSource(
    positionLocation: GLAttributeIndex = 0,
    vararg extraAttributes: ShaderAttribute,
) = createPassThroughVertexShaderSource(positionLocation, extraAttributes.toList())

private const val PASS_THROUGH_VERTEX_SHADER_TEMPLATE = """
#version 460 core

layout (location = %POSITION_LOCATION%) in vec3 v_position;
%EXTRA_IN%

out vec3 f_position;
%EXTRA_OUT%

void main() {
    gl_Position = vec4(v_position, 1);
    f_position = v_position;
    %PASS_THROUGH%
}
"""

package me.exerro.eggli.util

import me.exerro.eggli.types.GLAttributeIndex

/** TODO */
fun createModelVertexShaderSource(
    positionLocation: GLAttributeIndex = 0,
    normalLocation: GLAttributeIndex? = null,
    passThroughAttributes: Iterable<ShaderAttribute>,
): String {
    var result = PASS_THROUGH_VERTEX_SHADER_TEMPLATE
        .replace("%POSITION_LOCATION%", positionLocation.toString())
        .replace("%EXTRA_IN%", passThroughAttributes.joinToString("\n") { "layout (location = ${it.index}) in ${it.type} v_${it.name};" })
        .replace("%EXTRA_OUT%", passThroughAttributes.joinToString("\n") { "out ${it.type} f_${it.name};" })
        .replace("%PASS_THROUGH%", passThroughAttributes.joinToString("\n\t") { "f_${it.name} = v_${it.name};" })

    result = if (normalLocation != null) result
        .replace("%NORMAL_IN%", "layout (location = $normalLocation) in vec3 v_normal;")
        .replace("%NORMAL_OUT%", "out vec3 f_normal;")
        .replace("%NORMAL_MAIN%", "f_normal = (transpose(inverse(u_modelMatrix)) * vec4(v_normal, 0)).xyz;")
    else result
        .replace("%NORMAL_IN%", "")
        .replace("%NORMAL_OUT%", "")
        .replace("%NORMAL_MAIN%", "")

    return result
}

/** Vararg version of [createModelVertexShaderSource]. */
fun createModelVertexShaderSource(
    positionLocation: GLAttributeIndex = 0,
    normalLocation: GLAttributeIndex? = null,
    vararg passThroughAttributes: ShaderAttribute,
) = createModelVertexShaderSource(positionLocation, normalLocation, passThroughAttributes.toList())

private const val PASS_THROUGH_VERTEX_SHADER_TEMPLATE = """
#version 460 core

uniform mat4 u_projectionMatrix;
uniform mat4 u_viewMatrix;
uniform mat4 u_modelMatrix;

layout (location = %POSITION_LOCATION%) in vec3 v_position;
%NORMAL_IN%
%EXTRA_IN%

out vec3 f_position;
%NORMAL_OUT%
%EXTRA_OUT%

void main() {
    gl_Position = u_projectionMatrix * u_viewMatrix * u_modelMatrix * vec4(v_position, 1);
    f_position = (u_modelMatrix * vec4(v_position, 1)).xyz;
    %NORMAL_MAIN%
    %PASS_THROUGH%
}
"""

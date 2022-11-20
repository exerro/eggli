package `06_ssao`

const val MODEL_FRAGMENT_SHADER_SOURCE = """
#version 460 core

in vec3 f_position;
in vec2 f_uv;
in vec3 f_normal;
in vec4 f_colour;

layout (location = 0) out vec4 o_colour;
layout (location = 1) out vec4 o_position;
layout (location = 2) out vec4 o_normal;

void main() {
    o_colour = f_colour;
    o_position = vec4(f_position, 1);
    o_normal = vec4(f_normal, 1);
}
"""

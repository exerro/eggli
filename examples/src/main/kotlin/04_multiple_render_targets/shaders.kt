package `04_multiple_render_targets`

const val MODEL_FRAGMENT_SHADER_SOURCE = """
#version 460 core

uniform sampler2D u_texture;

in vec3 f_position;
in vec2 f_uv;
in vec3 f_normal;
in vec4 f_colour;

layout (location = 0) out vec4 o_colour;
layout (location = 1) out vec4 o_position;
layout (location = 2) out vec4 o_normal;

void main() {
    o_colour = vec4((f_colour * texture(u_texture, f_uv)).xyz, 1);
    o_position = vec4(f_position, 1);
    o_normal = vec4(f_normal, 1);
}
"""

const val SCREEN_FRAGMENT_SHADER_SOURCE = """
#version 460 core

uniform sampler2D u_texture;

in vec2 f_uv;

out vec4 o_colour;

void main() {
    o_colour.xyz = texture(u_texture, f_uv).xyz;
    o_colour.w = 1;
}
"""

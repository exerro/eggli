package `03_textured_cube`

const val VERTEX_SHADER_SOURCE = """
#version 460 core

uniform float u_time;
uniform mat4 u_projectionMatrix;

layout (location = 0) in vec3 v_position;
layout (location = 1) in vec2 v_uv;
layout (location = 3) in vec4 v_colour;

out vec2 f_uv;
out vec4 f_colour;

void main() {
    float sT = sin(u_time);
    float cT = cos(u_time);
    
    vec3 v = v_position;
    v.xz = vec2(v.x * cT - v.z * sT, v.x * sT + v.z * cT);
    v.z -= 4;
    
    gl_Position = u_projectionMatrix * vec4(v, 1);
    f_uv = v_uv;
    f_colour = v_colour;
}
"""

const val FRAGMENT_SHADER_SOURCE = """
#version 460 core

uniform sampler2D u_texture;

in vec2 f_uv;
in vec4 f_colour;

out vec4 o_colour;

void main() {
    o_colour = vec4((f_colour * texture(u_texture, f_uv)).xyz, 1);
}
"""

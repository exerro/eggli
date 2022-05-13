package `02_hello_face`

/** Pass-through vertex shader. */
const val VERTEX_SHADER_SOURCE = """
#version 460 core

layout (location = 0) in vec3 v_position;
layout (location = 3) in vec4 v_colour;

out vec4 f_colour;

void main() {
    gl_Position = vec4(v_position, 1);
    f_colour = v_colour;
}
"""

/** Draw fragments using the vertex colour fading in and out from transparent. */
const val FRAGMENT_SHADER_SOURCE = """
#version 460 core

uniform float u_time;

in vec4 f_colour;

out vec4 o_colour;

void main() {
    o_colour = vec4(f_colour.xyz, (1 + cos(u_time)) / 2);
}
"""

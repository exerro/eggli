package me.exerro.egglix.postprocessing

/**
 * A simple shader that passes through UVs to a fragment shader and uses a 2D
 * X/Y vertex position.
 */
const val VERTEX_FULLSCREEN_PASS_THROUGH_SHADER = """
#version 460 core

layout (location = 0) in vec2 v_position;
layout (location = 2) in vec2 v_uv;

out vec2 f_uv;

void main() {
    gl_Position = vec4(v_position, 0, 1);
    f_uv = v_uv;
}
"""

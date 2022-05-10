package me.exerro.eggli.enum

import org.lwjgl.opengl.GL46C

/** TODO */
enum class GLShaderType(val glValue: Int, val glName: String) {
    /** TODO */
    Fragment(GL46C.GL_FRAGMENT_SHADER, glName = "GL_FRAGMENT_SHADER"),

    /** TODO */
    Vertex(GL46C.GL_VERTEX_SHADER, glName = "GL_VERTEX_SHADER"),

    /** TODO */
    Geometry(GL46C.GL_GEOMETRY_SHADER, glName = "GL_GEOMETRY_SHADER"),

    /** TODO */
    TesselationControl(GL46C.GL_TESS_CONTROL_SHADER, glName = "GL_TESS_CONTROL_SHADER"),

    /** TODO */
    TesselationEvaluation(GL46C.GL_TESS_EVALUATION_SHADER, glName = "GL_TESS_EVALUATION_SHADER"),

    /** TODO */
    Compute(GL46C.GL_COMPUTE_SHADER, glName = "GL_COMPUTE_SHADER");

    override fun toString() = glName

    /** @see GLShaderType */
    companion object {
        /** TODO */
        fun fromGLValue(glValue: Int) = values()
            .firstOrNull { it.glValue == glValue }
    }
}

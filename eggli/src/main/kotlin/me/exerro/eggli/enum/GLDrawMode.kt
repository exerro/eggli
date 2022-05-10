package me.exerro.eggli.enum

import org.lwjgl.opengl.GL46C

/** TODO */
enum class GLDrawMode(val glValue: Int, val glName: String) {
    /** TODO */
    Points(GL46C.GL_POINTS, glName = "GL_POINTS"),

    /** TODO */
    LineStrip(GL46C.GL_LINE_STRIP, glName = "GL_LINE_STRIP"),

    /** TODO */
    LineLoop(GL46C.GL_LINE_LOOP, glName = "GL_LINE_LOOP"),

    /** TODO */
    Lines(GL46C.GL_LINES, glName = "GL_LINES"),

    /** TODO */
    LineStripAdjacency(GL46C.GL_LINE_STRIP_ADJACENCY, glName = "GL_LINE_STRIP_ADJACENCY"),

    /** TODO */
    LinesAdjacency(GL46C.GL_LINES_ADJACENCY, glName = "GL_LINES_ADJACENCY"),

    /** TODO */
    TriangleStrip(GL46C.GL_TRIANGLE_STRIP, glName = "GL_TRIANGLE_STRIP"),

    /** TODO */
    TriangleFan(GL46C.GL_TRIANGLE_FAN, glName = "GL_TRIANGLE_FAN"),

    /** TODO */
    Triangles(GL46C.GL_TRIANGLES, glName = "GL_TRIANGLES"),

    /** TODO */
    TriangleStripAdjacency(GL46C.GL_TRIANGLE_STRIP_ADJACENCY, glName = "GL_TRIANGLE_STRIP_ADJACENCY"),

    /** TODO */
    TrianglesAdjacency(GL46C.GL_TRIANGLES_ADJACENCY, glName = "GL_TRIANGLES_ADJACENCY"),

    /** TODO */
    Patches(GL46C.GL_PATCHES, glName = "GL_PATCHES");

    override fun toString() = glName

    /** @see GLDrawMode */
    companion object {
        /** TODO */
        fun fromGLValue(glValue: Int) = values()
            .firstOrNull { it.glValue == glValue }
    }
}

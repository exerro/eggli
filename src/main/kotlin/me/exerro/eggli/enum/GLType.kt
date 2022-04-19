package me.exerro.eggli.enum

import org.lwjgl.opengl.GL46C

/** TODO */
enum class GLType(val glValue: kotlin.Int, val glName: String) {
    /** TODO */
    Byte(GL46C.GL_BYTE, glName = "GL_BYTE"),

    /** TODO */
    UnsignedByte(GL46C.GL_UNSIGNED_BYTE, glName = "GL_UNSIGNED_BYTE"),

    /** TODO */
    Short(GL46C.GL_SHORT, glName = "GL_SHORT"),

    /** TODO */
    UnsignedShort(GL46C.GL_UNSIGNED_SHORT, glName = "GL_UNSIGNED_SHORT"),

    /** TODO */
    Int(GL46C.GL_INT, glName = "GL_INT"),

    /** TODO */
    UnsignedInt(GL46C.GL_UNSIGNED_INT, glName = "GL_UNSIGNED_INT"),

    /** TODO */
    HalfFloat(GL46C.GL_HALF_FLOAT, glName = "GL_HALF_FLOAT"),

    /** TODO */
    Float(GL46C.GL_FLOAT, glName = "GL_FLOAT"),

    /** TODO */
    Double(GL46C.GL_DOUBLE, glName = "GL_DOUBLE"),

    /** TODO */
    Fixed(GL46C.GL_FIXED, glName = "GL_FIXED"),

    /** TODO */
    Int2101010Rev(GL46C.GL_INT_2_10_10_10_REV, glName = "GL_INT_2_10_10_10_REV"),

    /** TODO */
    UnsignedInt2101010Rev(GL46C.GL_UNSIGNED_INT_2_10_10_10_REV, glName = "GL_UNSIGNED_INT_2_10_10_10_REV"),

    /** TODO */
    UnsignedInt10f11f11fRev(GL46C.GL_UNSIGNED_INT_10F_11F_11F_REV, glName = "GL_UNSIGNED_INT_10F_11F_11F_REV");

    override fun toString() = glName

    /** @see GLType */
    companion object {
        /** TODO */
        fun fromGLValue(glValue: kotlin.Int) = values()
            .firstOrNull { it.glValue == glValue }
    }
}

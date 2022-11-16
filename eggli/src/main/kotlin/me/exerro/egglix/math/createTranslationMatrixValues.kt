package me.exerro.egglix.math

/**
 * Create a row major array of float values representing a 4x4 translation
 * transform that translates vectors by ([dx], [dy], [dz]).
 */
fun createTranslationMatrixValues(
    dx: Float = 0f,
    dy: Float = 0f,
    dz: Float = 0f,
): FloatArray {
    return floatArrayOf(
        1f, 0f, 0f, dx,
        0f, 1f, 0f, dy,
        0f, 0f, 1f, dz,
        0f, 0f, 0f, 1f,
    )
}

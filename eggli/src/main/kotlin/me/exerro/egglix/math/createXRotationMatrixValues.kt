package me.exerro.egglix.math

import kotlin.math.cos
import kotlin.math.sin

/**
 * Create a row major array of float values representing a 4x4 rotation
 * transform that rotates vectors in the X axis by [theta] radians
 * counter-clockwise.
 */
fun createXRotationMatrixValues(
    theta: Float,
): FloatArray {
    val sT = sin(theta)
    val cT = cos(theta)
    return floatArrayOf(
        1f, 0f,  0f, 0f,
        0f, cT, -sT, 0f,
        0f, sT,  cT, 0f,
        0f, 0f,  0f, 1f,
    )
}

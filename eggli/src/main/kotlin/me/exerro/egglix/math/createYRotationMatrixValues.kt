package me.exerro.egglix.math

import kotlin.math.cos
import kotlin.math.sin

/**
 * Create a row major array of float values representing a 4x4 rotation
 * transform that rotates vectors in the Y axis by [theta] radians
 * counter-clockwise.
 */
fun createYRotationMatrixValues(
    theta: Float,
): FloatArray {
    val sT = sin(theta)
    val cT = cos(theta)
    return floatArrayOf(
         cT, 0f, sT, 0f,
         0f, 1f, 0f, 0f,
        -sT, 0f, cT, 0f,
         0f, 0f, 0f, 1f,
    )
}

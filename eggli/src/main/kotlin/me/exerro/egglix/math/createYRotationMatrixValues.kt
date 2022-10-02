package me.exerro.egglix.math

import kotlin.math.sin
import kotlin.math.cos

/** TODO */
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

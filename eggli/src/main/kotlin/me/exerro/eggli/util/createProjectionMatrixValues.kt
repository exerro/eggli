package me.exerro.eggli.util

import kotlin.math.tan

/** TODO */
fun createPerspectiveProjectionMatrixValues(
    aspectRatio: Float,
    fov: Float = `120_DEGREES`,
    near: Float = 0.001f,
    far: Float = 1000f,
) = floatArrayOf(
    1 / tan(fov / 2) / aspectRatio, 0f, 0f, 0f,
    0f, 1 / tan(fov / 2), 0f, 0f,
    0f, 0f, -(far + near) / (far - near), -2f * far * near / (far - near),
    0f, 0f, -1f, 1f, // anything found online shows this final element to be 0
    // for whatever reason, having 0 means that depth buffers become non-sensical
    // idk why this works but it works so don't touch it pls
)

private const val `120_DEGREES` = 2.0944f

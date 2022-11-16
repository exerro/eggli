package me.exerro.egglix.math

import kotlin.math.tan

/**
 * Create a row major array of float values representing a 4x4 perspective
 * projection matrix with the given [aspectRatio], [fov], and [near] and [far]
 * clipping distances.
 * @param aspectRatio measures the ratio of width:height of the screen i.e.
 *                    `width / height`
 * @param fov measures the angle between the lower and upper edges of the screen
 *            in world space.
 * @param near measures the distance to near objects where clipping starts to
 *             occur
 * @param far measures the distance to far objects where clipping starts to
 *            occur
 */
fun createPerspectiveProjectionMatrixValues(
    aspectRatio: Float,
    fov: Float = `120_DEGREES`,
    near: Float = 0.001f,
    far: Float = 1000f,
) = floatArrayOf(
    1 / tan(fov / 2) / aspectRatio, 0f, 0f, 0f,
    0f, 1 / tan(fov / 2), 0f, 0f,
    0f, 0f, -(far + near) / (far - near), -2f * far * near / (far - near),
    0f, 0f, -1f, 0f,
)

private const val `120_DEGREES` = 2.0944f

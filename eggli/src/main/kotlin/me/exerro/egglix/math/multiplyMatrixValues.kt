package me.exerro.egglix.math

/**
 * Takes two row major arrays of float values representing 4x4 transformations
 * and returns a similar array representing the combined affect of applying [b]
 * and then [a].
 */
fun multiplyMatrixValues(a: FloatArray, b: FloatArray, vararg c: FloatArray): FloatArray {
    require(a.size == 16)
    require(b.size == 16)

    val result = FloatArray(16) { index ->
        val i = index / 4
        val j = index % 4

        (0 .. 3).map {
            a[i * 4 + it] * b[it * 4 + j]
        } .sum()
    }

    return if (c.isEmpty()) result else
        multiplyMatrixValues(result, c[0], *c.drop(1).toTypedArray())
}

package me.exerro.egglix.math

/** TODO */
fun multiplyMatrixValues(a: FloatArray, b: FloatArray): FloatArray {
    require(a.size == 16)
    require(b.size == 16)

    return FloatArray(16) { index ->
        val i = index / 4
        val j = index % 4

        (0 .. 3).map {
            a[i * 4 + it] * b[it * 4 + j]
        } .sum()
    }
}

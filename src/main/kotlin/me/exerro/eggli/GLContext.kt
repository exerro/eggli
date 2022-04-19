package me.exerro.eggli

/** TODO */
class GLContext internal constructor(
    /** TODO */
    val worker: GLWorker,
) {
    /** TODO */
    operator fun <T> GL<T>.component1() = evaluate()
}

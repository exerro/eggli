package me.exerro.eggli.util

/** TODO */
interface RenderLoopHandle {
    /** TODO */
    val isRunning: Boolean

    /** TODO */
    suspend fun stop()

    /** TODO */
    fun stopLater()

    /** TODO */
    fun stopSync()
}

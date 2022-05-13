package me.exerro.eggli.util

/**
 * A handle to control whether a render loop continues running.
 *
 * @see stop
 * @see stopLater
 * @see stopBlocking
 * @see createRenderLoop
 */
interface RenderLoopHandle {
    /** Whether the render loop is currently running. */
    val isRunning: Boolean

    /** Stop the render loop, resuming execution once the loop has stopped. */
    suspend fun stop()

    /**
     * Stop the render loop at some point in the future, resuming execution
     * immediately.
     */
    fun stopLater()

    /**
     * Stop the render loop, blocking the current thread until the render loop
     * has stopped.
     */
    fun stopBlocking()
}

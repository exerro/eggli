package me.exerro.eggli

import me.exerro.lifetimes.Lifetime

/** TODO */
context (GLContext, Lifetime)
open class GLResource<T>(
    /** TODO */
    private val value: T,
    /** TODO */
    private val destructor: (T) -> Unit,
) {
    /** TODO */
    var isDestroyed = false; private set

    /** TODO */
    fun get() = synchronized(lock) {
        if (!isDestroyed) value
        else throw DestroyedResourceException(
            "Attempt to access destroyed resource ($this): $value"
        )
    }

    /** TODO */
    fun destroy() {
        val wasDestroyed = synchronized(lock) {
            lifetimeBinding?.disconnect()
            isDestroyed.also { isDestroyed = true }
        }
        if (!wasDestroyed) worker.runLater(runIfStopped = true) { destructor(value) }
    }

    /** TODO */
    class DestroyedResourceException(override val message: String): Exception()

    override fun equals(other: Any?) = value == other
    override fun hashCode() = value.hashCode()
    override fun toString() = value.toString()

    private val lock = Any()
    private val lifetimeBinding = onLifetimeEnded {
        val wasDestroyed = synchronized(lock) {
            isDestroyed.also { isDestroyed = true }
        }
        if (!wasDestroyed) worker.runLater(runIfStopped = true) { destructor(value) }
    }
}

package me.exerro.eggli

import java.util.concurrent.CountDownLatch
import java.util.concurrent.atomic.AtomicBoolean

/** TODO */
class GL<T> internal constructor(
    internal val evaluater: context (GLContext) () -> T,
) {
    /** TODO */
    @Volatile
    var isEvaluated = false; private set

    /** TODO */
    @Volatile
    var value = null as T?; private set

    /** TODO */
    fun <R> map(fn: (T) -> R) = GL {
        val (value) = this@GL
        fn(value)
    }

    /** TODO */
    fun <R> flatMap(fn: (T) -> GL<R>) = GL {
        val (value) = this@GL
        val (result) = fn(value)
        result
    }

    /** TODO */
    context (GLContext)
    internal fun evaluate(): T =
        if (!evaluating.getAndSet(true)) {
            val evaluated = evaluater(this@GLContext)
            value = evaluated
            isEvaluated = true
            evaluationLatch.countDown()
            evaluated
        }
        else {
            evaluationLatch.await()
            value!!
        }

    /** TODO */
    private val evaluating = AtomicBoolean(false)

    /** TODO */
    private val evaluationLatch = CountDownLatch(1)

    /** @see GL */
    companion object {
        /** TODO */
        operator fun <T> invoke(evaluate: context (GLContext) () -> T) =
            GL(evaluate)
    }
}

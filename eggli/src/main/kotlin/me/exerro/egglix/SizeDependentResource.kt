package me.exerro.egglix

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLResource
import me.exerro.lifetimes.Lifetime

/** TODO */
class SizeDependentResource<T> private constructor(
    currentWidth: Int,
    currentHeight: Int,
    private var currentResource: GLResource<T>,
    private val createFn: context (GLContext, Lifetime) (Int, Int) -> GLResource<T>,
) {
    /** TODO */
    var currentWidth = currentWidth; private set

    /** TODO */
    var currentHeight = currentHeight; private set

    /** TODO */
    context (GLContext, Lifetime)
    fun resize(width: Int, height: Int) {
        if (width == currentWidth && height == currentHeight)
            return

        currentResource.destroy()
        currentResource = createFn(this@GLContext, this@Lifetime, width, height)
    }

    /** @see SizeDependentResource */
    companion object {
        context (GLContext, Lifetime)
        fun <T> create(
            width: Int,
            height: Int,
            create: context (GLContext, Lifetime) (Int, Int) -> GLResource<T>,
        ): SizeDependentResource<T> =
            SizeDependentResource(width, height, create(GLContext.get(), this@Lifetime, width, height), create)
    }
}

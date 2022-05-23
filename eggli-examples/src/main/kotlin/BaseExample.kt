import me.exerro.eggli.GL
import me.exerro.eggli.GLContext
import me.exerro.lifetimes.Lifetime

abstract class BaseExample<Data: Any> {
    context (Lifetime)
    abstract fun createData(): GL<Data>

    context (GLContext)
    abstract fun renderFrame(data: Data, time: Float)
}

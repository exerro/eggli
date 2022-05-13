import me.exerro.eggli.GL
import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.lifetimes.Lifetime

abstract class BaseExample<Data: Any> {
    context (Lifetime, GLDebugger.Context)
    abstract fun createData(): GL<Data>

    context (GLContext, GLDebugger.Context)
    abstract fun renderFrame(data: Data, time: Float)
}

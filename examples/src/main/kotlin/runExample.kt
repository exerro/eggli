import me.exerro.eggli.GL
import me.exerro.egglix.RenderLoopHandle
import me.exerro.egglix.createGLFWWindowWithWorker
import me.exerro.egglix.createRenderLoop
import me.exerro.lifetimes.withLifetime
import org.lwjgl.glfw.GLFW

const val WINDOW_WIDTH = 1080
const val WINDOW_HEIGHT = 720

/**
 * An extended version of the code found in 01_hello_window, additionally
 * handling errors in the setup and rendering phases, and ignoring more log
 * messages during rendering.
 */
inline fun <reified E: BaseExample<*>> runExample() {
    GLFW.glfwInit()

    @Suppress("DEPRECATION", "UNCHECKED_CAST")
    val example = E::class.java.newInstance() as BaseExample<Any>

    withLifetime {
        val (windowId, worker) = createGLFWWindowWithWorker(
            width = WINDOW_WIDTH,
            height = WINDOW_HEIGHT,
            title = "Eggli Example Window (${E::class.java.packageName})",
        )
        val renderingData = worker.evaluateBlocking(GL {
            try {
                example.createData().get()
            }
            catch (e: Throwable) {
                e.printStackTrace()
                null
            }
        })

        /**
         * If [renderingData] is null, the createData() call above must've
         * thrown an exception, so we shouldn't start rendering.
         */
        if (renderingData != null) {
            val t0 = System.currentTimeMillis()
            lateinit var renderLoopHandle: RenderLoopHandle

            renderLoopHandle = createRenderLoop(windowId, worker) {
                /**
                 * Try rendering a frame. If an exception is thrown,
                 * stop the rendering loop and close the window.
                 */
                try {
                    example.renderFrame(
                        data = renderingData,
                        time = (System.currentTimeMillis() - t0) / 1000f
                    )
                }
                catch (e: Throwable) {
                    e.printStackTrace()
                    GLFW.glfwSetWindowShouldClose(windowId, true)
                    renderLoopHandle.stopLater()
                }
            }

            while (!GLFW.glfwWindowShouldClose(windowId)) {
                GLFW.glfwWaitEvents()
            }

            renderLoopHandle.stopBlocking()
        }
    }
}

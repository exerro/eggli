package `01_hello_window`

import me.exerro.eggli.GLWorker
import me.exerro.eggli.enum.GL_COLOR_BUFFER_BIT
import me.exerro.eggli.gl.glClear
import me.exerro.eggli.gl.glClearColor
import me.exerro.egglix.createGLFWRenderLoop
import me.exerro.egglix.createGLFWWindowWithWorker
import me.exerro.egglix.createRenderLoop
import me.exerro.lifetimes.Lifetime
import me.exerro.lifetimes.withLifetime
import org.lwjgl.glfw.GLFW

/**
 * This example shows a minimal amount of code to create a window and fill it
 * with a fixed colour. It includes all the boilerplate required, with
 * explanations.
 */
fun main() {
    /** First, initialise GLFW, so we can create our window. */
    GLFW.glfwInit()

    /**
     * Allocations in OpenGL are usually managed explicitly, i.e. you generate
     * or create an object, then explicitly destroy or delete it later. In
     * Eggli, this process is handled automatically using [Lifetime]s. Many
     * operations are wrapped in a lifetime so that resources are automatically
     * destroyed when the lifetime ends. There's a lot of flexibility to
     * lifetimes, but here we just want a lifetime spanning the whole duration
     * of the application. When we get to the end of the block, all GL resources
     * allocated within would be destroyed, although there aren't any in this
     * example.
     */
    withLifetime {
        /**
         * Here we create our window and worker. OpenGL is inherently single
         * threaded, making it awkward to do multithreaded graphics. This is
         * particularly annoying with GLFW since resizing a window will
         * block the `01_hello_window`.main thread, preventing graphics
         * updates if you're rendering on the main thread. To account for
         * this, Eggli does all OpenGL operations on a separate thread. You
         * can interface with this thread through a [GLWorker], which this
         * function creates for you (along with the OpenGL rendering
         * thread.)
         */
        val (windowId, worker) = createGLFWWindowWithWorker(title = "Eggli Hello Window Example")

        /**
         * To run our first OpenGL call, we need to be running on the OpenGL
         * thread. [GLWorker.runLater] lets us submit a function to run on
         * that thread without waiting for it to finish. This is all we need
         * in this case, since we don't need to wait for the clear colour to
         * be set.
         * Note: many functions like glClearColor expect a GLContext to be
         * in scope (which represents running on the OpenGL thread.) The
         * worker functions introduce this context into the scope of the
         * function provided.
         */
        worker.runLater {
            glClearColor(0.1f, 0.4f, 0.7f)
        }

        /**
         * Since everything OpenGL related is running on a single thread, we
         * can't have a normal `while` loop to draw, as that would block
         * anything else wanting to run on the thread. To get around this, a
         * utility function [createRenderLoop] is provided, which will run
         * the function on the OpenGL thread and queue it to run again
         * afterwards forever. You can tell this to stop through the handle
         * that's returned.
         * Here, we introduce the rendering debug context, and start our
         * render loop which clears the screen's colour buffer.
         */
        val renderLoopHandle = createGLFWRenderLoop(windowId, worker) {
            glClear(GL_COLOR_BUFFER_BIT)
        }

        /**
         * Now everything is set up to draw, we need to handle window events
         * so that when the user closes the window we can stop the program.
         */
        while (!GLFW.glfwWindowShouldClose(windowId)) {
            GLFW.glfwWaitEvents()
        }

        /**
         * Once the loop above has stopped, the application should quit. If
         * we were to have nothing here, the lifetime would end, and any
         * resources we'd allocated would be cleaned up. However, the render
         * loop that we created would still be running and potentially
         * trying to use resources that had just been cleaned up.
         * As such, we explicitly end the render loop and block this thread
         * until it has fully stopped.
         */
        renderLoopHandle.stopBlocking()
    }

    /**
     * Here, the [Lifetime] we wrapped the code above in will have ended, and
     * any rendering objects we allocated will either be destroyed, or queued to
     * be destroyed. The application thread will stop soon.
     */
}

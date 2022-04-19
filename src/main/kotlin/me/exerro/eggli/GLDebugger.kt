package me.exerro.eggli

import org.lwjgl.opengl.GL46C
import java.io.PrintStream

/** TODO */
interface GLDebugger {
    /** TODO */
    fun glLog(action: LogAction, entity: LogEntity, message: String)

    /** TODO */
    fun glCheckForErrors()

    /** TODO */
    fun countObjects(entity: LogEntity): Int

    /** TODO */
    enum class LogAction {
        ObjectCreated,
        ObjectDestroyed,
        ObjectBound,
        ObjectUnbound,
        StateChanged,
        Generic,
    }

    /** TODO */
    enum class LogEntity {
        State,
        FBuffer,
        Texture,
        Buffer,
        VArray,
        Window,
    }

    /** @see GLDebugger */
    companion object {
        /** TODO */
        fun createDefault(
            outputStream: PrintStream = System.out,
            ansiColouring: Boolean = true,
        ) = object: GLDebugger {
            override fun glLog(action: LogAction, entity: LogEntity, message: String) {
                val dt = System.currentTimeMillis() - t0
                val ms = dt % 1000
                val s = dt / 1000 % 60
                val m = dt / 60000 % 60
                val h = dt / 60000 % 60

                if (action == LogAction.ObjectCreated)
                    counts[entity] = counts[entity]!! + 1

                if (action == LogAction.ObjectDestroyed)
                    counts[entity] = counts[entity]!! - 1

                val format = if (ansiColouring) colouredLogFormat else logFormat

                outputStream.println(format.format(h, m, s, ms, entity.name, action.name, message))
            }

            override fun glCheckForErrors() = when (GL46C.glGetError()) {
                GL46C.GL_NO_ERROR -> Unit
                GL46C.GL_INVALID_ENUM -> error("GL_INVALID_ENUM")
                GL46C.GL_INVALID_VALUE -> error("GL_INVALID_VALUE")
                GL46C.GL_INVALID_OPERATION -> error("GL_INVALID_OPERATION")
                GL46C.GL_INVALID_FRAMEBUFFER_OPERATION -> error("GL_INVALID_FRAMEBUFFER_OPERATION")
                GL46C.GL_OUT_OF_MEMORY -> error("GL_OUT_OF_MEMORY")
                GL46C.GL_STACK_UNDERFLOW -> error("GL_STACK_UNDERFLOW")
                GL46C.GL_STACK_OVERFLOW -> error("GL_STACK_OVERFLOW")
                else -> error("Unknown GL error")
            }

            override fun countObjects(entity: LogEntity) =
                counts[entity]!!

            private val counts = LogEntity.values().associateWith { 0 } .toMutableMap()
            private val t0 = System.currentTimeMillis()
        }

        private val actionMaxLength = LogAction.values().maxOf { it.name.length }
        private val entityMaxLength = LogEntity.values().maxOf { it.name.length }
        private val logFormat = "[%02d:%02d:%02d::%03d] (%${entityMaxLength}s) %${actionMaxLength}s :: %s"
        private val colouredLogFormat = "\u001b[37m[\u001b[0m%02d:%02d:%02d::%03d\u001b[37m] (\u001b[36m%-${entityMaxLength}s\u001b[37m) \u001b[36m%-${actionMaxLength}s\u001b[37m :: \u001b[0m%s"
    }
}

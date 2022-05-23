package me.exerro.eggli

import java.io.OutputStream
import java.io.PrintStream

/** TODO */
interface GLDebugger {
    /** TODO */
    fun log(action: LogAction, entity: LogEntity, message: String)

    /** TODO */
    fun log(action: LogAction, entity: LogEntity, fn: (PrintStream) -> Unit) {
        val data = StringBuilder()
        val stream = object: OutputStream() {
            override fun write(b: Int) { data.appendCodePoint(b) }
        }
        fn(PrintStream(stream))
        log(action, entity, data.toString().trim())
    }

    /**
     * Return a modified version of this [GLDebugger] which adds ANSI colouring
     * to log messages according to the specific action.
     * Default colours are:
     *
     *                  ObjectCreated: 32
     *                  ObjectDestroyed: 33
     *                  ObjectBound: 35
     *                  ObjectUnbound: 35
     *                  StateChanged: 34
     *                  UniformChanged: 34
     *                  Generic: 37
     *                  Info: 30
     *                  DrawCall: 30
     *                  Data: 34
     *                  Error: 31
     *
     * @param colourMap Map of actions to ANSI colour codes (e.g. "31" for red).
     *                  Any missing keys will fall back on defaults.
     */
    fun ansiColoured(
        colourMap: Map<LogAction, String> = emptyMap(),
    ) = object: GLDebugger {
        override fun log(action: LogAction, entity: LogEntity, message: String) {
            val colouring = when (action) {
                LogAction.ObjectCreated -> "32"
                LogAction.ObjectDestroyed -> "33"
                LogAction.ObjectBound -> "35"
                LogAction.ObjectUnbound -> "35"
                LogAction.StateChanged -> "34"
                LogAction.UniformChanged -> "34"
                LogAction.Generic -> "37"
                LogAction.Info -> "30"
                LogAction.DrawCall -> "30"
                LogAction.Data -> "34"
                LogAction.Error -> "31"
            }
            this@GLDebugger.log(action, entity, "\u001b[${colouring}m$message\u001b[0m")
        }
    }

    /**
     * Return a modified version of this [GLDebugger] which ignores all messages
     * that have the [action][LogAction] specified by [ignoreAction].
     */
    fun ignoringAction(
        ignoreAction: LogAction,
    ) = object: GLDebugger {
        override fun log(action: LogAction, entity: LogEntity, message: String) {
            if (action != ignoreAction) this@GLDebugger.log(action, entity, message)
        }
    }

    /**
     * Return a modified version of this [GLDebugger] which ignores all messages
     * that have the [entity][LogEntity] specified by [ignoreEntity].
     */
    fun ignoringEntity(
        ignoreEntity: LogEntity,
    ) = object: GLDebugger {
        override fun log(action: LogAction, entity: LogEntity, message: String) {
            if (entity != ignoreEntity) this@GLDebugger.log(action, entity, message)
        }
    }

    /**
     * Return a modified version of this [GLDebugger] which ignores all messages
     * that have both the [action][LogAction] and [entity][LogEntity]
     * specified by [ignoreAction] and [ignoreEntity].
     */
    fun ignoring(
        ignoreAction: LogAction,
        ignoreEntity: LogEntity,
    ) = object: GLDebugger {
        override fun log(action: LogAction, entity: LogEntity, message: String) {
            if (action != ignoreAction || entity != ignoreEntity)
                this@GLDebugger.log(action, entity, message)
        }
    }

    /** TODO */
    interface Context {
        /** TODO */
        val glDebugger: GLDebugger

        /** TODO */
        fun glLog(action: LogAction, entity: LogEntity, message: String) =
            glDebugger.log(action, entity, message)

        /** @see Context */
        companion object {
            /** TODO */
            operator fun invoke(debugger: GLDebugger) = object: Context {
                override val glDebugger = debugger
            }
        }
    }

    /**
     * A [LogAction] is a rough approximation of the types of actions one can
     * perform using OpenGL. Log messages are given a [LogAction] to help with
     * categorisation and filtering.
     *
     * * [ObjectCreated] - when an object is created
     * * [ObjectDestroyed] - when an object is destroyed
     * * [ObjectBound] - when an object is bound
     * * [ObjectUnbound] - when an object is unbound
     * * [StateChanged] - when some persistent state is changed
     * * [UniformChanged] - when a uniform value is changed
     * * [Info] - when information has been retrieved
     * * [Data] - when data has been assigned, e.g. setting buffer data
     * * [DrawCall] - when something is drawn
     * * [Error] - when something errors
     * * [Generic] - when any other action occurs
     */
    enum class LogAction {
        /** @see LogAction */ ObjectCreated,
        /** @see LogAction */ ObjectDestroyed,
        /** @see LogAction */ ObjectBound,
        /** @see LogAction */ ObjectUnbound,
        /** @see LogAction */ StateChanged,
        /** @see LogAction */ UniformChanged,
        /** @see LogAction */ Info,
        /** @see LogAction */ Data,
        /** @see LogAction */ DrawCall,
        /** @see LogAction */ Error,
        /** @see LogAction */ Generic,
    }

    /**
     * A [LogEntity] is a rough approximation of the entities one can perform
     * actions on using OpenGL. Log messages are given a [LogEntity] to help
     * with categorisation and filtering.
     *
     * * [State] - global OpenGL state
     * * [FBuffer] - a framebuffer
     * * [Texture] - a texture
     * * [Buffer] - a buffer
     * * [VArray] - a vertex array
     * * [Shader] - a shader
     * * [Program] - a shader program
     * * [Window] - a window
     * * [DrawTarget] - the current draw target (i.e. the screen or a bound
     *                  framebuffer)
     */
    enum class LogEntity {
        /** @see LogEntity */ State,
        /** @see LogEntity */ FBuffer,
        /** @see LogEntity */ Texture,
        /** @see LogEntity */ Buffer,
        /** @see LogEntity */ VArray,
        /** @see LogEntity */ Shader,
        /** @see LogEntity */ Program,
        /** @see LogEntity */ Window,
        /** @see LogEntity */ DrawTarget,
    }

    /** @see GLDebugger */
    companion object {
        /**
         * Create a default [GLDebugger] which writes messages to an output
         * stream. Messages are tagged with a timestamp and include the entity
         * and action, padded and aligned.
         * @param outputStream stream to write to, defaults to stdout
         * @param ansiColouring control whether decoration e.g. timestamps and
         *                      punctuation are ANSI coloured.
         */
        fun createDefault(
            outputStream: PrintStream = System.out,
            ansiColouring: Boolean = true,
        ) = object: GLDebugger {
            override fun log(action: LogAction, entity: LogEntity, message: String) {
                val dt = System.currentTimeMillis() - t0
                val ms = dt % 1000
                val s = dt / 1000 % 60
                val m = dt / 60000 % 60
                val h = dt / 3600000 % 60

                val format = if (ansiColouring) colouredLogFormat else logFormat
                val msg = message.replace("\n", "\n" + " ".repeat(logMessageIndentation))

                outputStream.println(format.format(h, m, s, ms, entity.name, action.name, msg))
            }

            private val t0 = System.currentTimeMillis()
        }

        private val actionMaxLength = LogAction.values().maxOf { it.name.length }
        private val entityMaxLength = LogEntity.values().maxOf { it.name.length }
        private val logFormat = "[%02d:%02d:%02d::%03d] (%${entityMaxLength}s, %${actionMaxLength}s) :: %s"
        private val colouredLogFormat = "\u001b[37m[\u001b[0m%02d:%02d:%02d::%03d\u001b[37m] (\u001b[36m%-${entityMaxLength}s\u001b[37m, \u001b[36m%-${actionMaxLength}s\u001b[37m) :: \u001b[0m%s"
        private val logMessageIndentation = logFormat.format(0, 0, 0, 0, "", "", "").length
    }
}

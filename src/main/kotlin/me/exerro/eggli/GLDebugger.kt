package me.exerro.eggli

import java.io.PrintStream

/** TODO */
interface GLDebugger {
    /** TODO */
    fun log(action: LogAction, entity: LogEntity, message: String)

    /** TODO */
    fun ansiColoured() = object: GLDebugger {
        override fun log(action: LogAction, entity: LogEntity, message: String) {
            val colouring = when (action) {
                LogAction.ObjectCreated -> "32"
                LogAction.ObjectDestroyed -> "33"
                LogAction.ObjectBound -> "35"
                LogAction.ObjectUnbound -> "35"
                LogAction.StateChanged -> "34"
                LogAction.Generic -> "37"
                LogAction.Error -> "31"
            }
            this@GLDebugger.log(action, entity, "\u001b[${colouring}m$message\u001b[0m")
        }
    }

    /** TODO */
    fun ignoringAction(
        ignoreAction: LogAction,
    ) = object: GLDebugger {
        override fun log(action: LogAction, entity: LogEntity, message: String) {
            if (action != ignoreAction) this@GLDebugger.log(action, entity, message)
        }
    }

    /** TODO */
    fun ignoringEntity(
        ignoreEntity: LogEntity,
    ) = object: GLDebugger {
        override fun log(action: LogAction, entity: LogEntity, message: String) {
            if (entity != ignoreEntity) this@GLDebugger.log(action, entity, message)
        }
    }

    /** TODO */
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

    /** TODO */
    enum class LogAction {
        ObjectCreated,
        ObjectDestroyed,
        ObjectBound,
        ObjectUnbound,
        StateChanged,
        Generic,
        Error,
    }

    /** TODO */
    enum class LogEntity {
        State,
        FBuffer,
        Texture,
        Buffer,
        VArray,
        Shader,
        Program,
        Window,
    }

    /** @see GLDebugger */
    companion object {
        /** TODO */
        fun createDefault(
            outputStream: PrintStream = System.out,
            ansiColouring: Boolean = true,
        ) = object: GLDebugger {
            override fun log(action: LogAction, entity: LogEntity, message: String) {
                val dt = System.currentTimeMillis() - t0
                val ms = dt % 1000
                val s = dt / 1000 % 60
                val m = dt / 60000 % 60
                val h = dt / 60000 % 60

                val format = if (ansiColouring) colouredLogFormat else logFormat
                val msg = message.replace("\n", "\n" + " ".repeat(logMessageIndentation))

                outputStream.println(format.format(h, m, s, ms, entity.name, action.name, msg))
            }

            private val t0 = System.currentTimeMillis()
        }

        private val actionMaxLength = LogAction.values().maxOf { it.name.length }
        private val entityMaxLength = LogEntity.values().maxOf { it.name.length }
        private val logFormat = "[%02d:%02d:%02d::%03d] (%${entityMaxLength}s) %${actionMaxLength}s :: %s"
        private val colouredLogFormat = "\u001b[37m[\u001b[0m%02d:%02d:%02d::%03d\u001b[37m] (\u001b[36m%-${entityMaxLength}s\u001b[37m) \u001b[36m%-${actionMaxLength}s\u001b[37m :: \u001b[0m%s"
        private val logMessageIndentation = logFormat.format(0, 0, 0, 0, "", "", "").length
    }
}

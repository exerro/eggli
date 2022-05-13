package me.exerro.eggli.util

import me.exerro.eggli.GLDebugger

/**
 * Wrap [debugger] in a [GLDebugger.Context] and run [block] with that context
 * in scope.
 */
fun <T> withDebugContext(
    debugger: GLDebugger = GLDebugger.createDefault(),
    block: context (GLDebugger.Context) () -> T
) = with(GLDebugger.Context(debugger), block)

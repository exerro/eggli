package me.exerro.eggli.util

import me.exerro.eggli.GLDebugger

/** TODO */
fun <T> withDebugContext(
    debugger: GLDebugger = GLDebugger.createDefault(),
    block: context (GLDebugger.Context) () -> T
) = with(GLDebugger.Context(debugger), block)

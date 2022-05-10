package me.exerro.esel.language

import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

/** Provide the [GenerationProcessor] to generate our source files. */
class GenerationProcessorProvider: SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment) =
        GenerationProcessor(
            codeGenerator = environment.codeGenerator,
            logger = environment.logger,
            options = environment.options
        )
}

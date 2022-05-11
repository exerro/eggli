package me.exerro.eggli.gen

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated

/** Generates eggli code. */
class GenerationProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger,
    private val options: Map<String, String>,
): SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        if (hasRun) return emptyList()

        // get the dependencies of the files we're creating
        val dependencies = Dependencies(aggregating = false)

        writeCodeFile(dependencies, "enum", "GLenum", buildGLenumFileContent())
        hasRun = true

        return emptyList()
    }

    private fun writeCodeFile(
        dependencies: Dependencies,
        packageName: String,
        name: String,
        content: String,
    ) {
        val file = codeGenerator.createNewFile(
            dependencies = dependencies,
            packageName = "me.exerro.eggli.$packageName",
            fileName = name,
        )
        file.write(content.toByteArray())
    }

    private var hasRun = false
}

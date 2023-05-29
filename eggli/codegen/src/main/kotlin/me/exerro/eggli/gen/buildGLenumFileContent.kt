package me.exerro.eggli.gen

import com.squareup.kotlinpoet.*

/** TODO */
fun buildGLenumFileContent(): String {
    val fileContent = FileSpec.builder("me.exerro.eggli.enum", "GLenum")
    val reverseLookup = mutableMapOf<GLenumDescriptor.Member, MutableList<String>>()

    for (e in enumData) {
        for (m in e.members) {
            reverseLookup.computeIfAbsent(m) { mutableListOf() } .add(e.name)
        }
    }

    for (e in enumData) {
        val cb = TypeSpec
            .companionObjectBuilder()
            .addKdoc("@see ${e.name}")
            .addFunction(FunSpec
                .builder("fromGLValue")
                .addKdoc("Convert a raw integral value from OpenGL to an instance of [${e.name}].\n")
                .addKdoc("@throws IllegalStateException when [glValue] is not a valid value for [${e.name}]\n")
                .addKdoc("@see ${e.name}")
                .addParameter("glValue", Int::class)
                .beginControlFlow("return·when·(glValue)")
                .also { builder ->
                    for (m in e.members) {
                        builder.addStatement("org.lwjgl.opengl.GL46C.${m.name}·->·me.exerro.eggli.enum.${m.name}")
                    }
                }
                .addStatement("else·->·error(%S.format(glValue))", "%d is not a valid value of ${e.name}")
                .endControlFlow()
                .build())

        for (m in e.members) {
            cb.addProperty(PropertySpec
                .builder(m.kotlinName, ClassName("me.exerro.eggli.enum", e.name))
                .addKdoc(m.docString)
                .addKdoc("\n@see me.exerro.eggli.enum." + m.name)
                .initializer("me.exerro.eggli.enum." + m.name)
                .build()
            )
        }

        val ib = TypeSpec
            .interfaceBuilder(e.name)
            .addKdoc(e.docString)
            .addModifiers(KModifier.SEALED)
            .addProperty(PropertySpec
                .builder("glName", String::class)
                .addKdoc("OpenGL name of this [${e.name}].")
                .build())
            .addProperty(PropertySpec
                .builder("glValue", Int::class)
                .addKdoc("OpenGL facing integer value of this [${e.name}].")
                .build())
            .addType(cb.build())
            .also { builder ->
                for (m in e.members) {
                    builder.addKdoc("\n@see ${m.kotlinName}")
                }
            }

        fileContent.addType(ib.build())
    }

    for ((m, es) in reverseLookup) {
        val pb = TypeSpec
            .objectBuilder(m.name)
            .addProperty(PropertySpec
                .builder("glName", String::class, KModifier.OVERRIDE)
                .initializer("\"${m.name}\"")
                .build())
            .addProperty(PropertySpec
                .builder("glValue", Int::class, KModifier.OVERRIDE)
                .initializer("org.lwjgl.opengl.GL46C.${m.name}")
                .build())
            .addFunction(FunSpec
                .builder("toString")
                .addModifiers(KModifier.OVERRIDE)
                .addCode("return \"${m.name}\"")
                .build())
            .addFunction(FunSpec
                .builder("hashCode")
                .addModifiers(KModifier.OVERRIDE)
                .addCode("return glValue")
                .build())
            .addFunction(FunSpec
                .builder("equals")
                .addParameter("other", Any::class.asTypeName().copy(nullable = true))
                .addModifiers(KModifier.OVERRIDE)
                .addCode("return other·is·${m.name}·||·glValue·==·other")
                .build())

        for (e in es) pb
            .addKdoc("\n@see $e.${m.kotlinName}")
            .addSuperinterface(ClassName("me.exerro.eggli.enum", e))

        fileContent.addType(pb.build())
    }

    return fileContent.build().toString()
}

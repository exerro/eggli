
plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":eggli"))
}

////////////////////////////////////////////////////////////////////////////////

fun createExampleTasks(name: String) {
    // Default task to run the example application
    task<JavaExec>(name) {
        group = "examples"
        description = "Run the $name example"
        classpath = sourceSets.main.get().runtimeClasspath
        mainClass.set("$name.MainKt")
    }

    // Produce a "fat jar" containing everything needed to run the example.
    val jar = task<Jar>("jar_${name}_complete") {
        archiveBaseName.set("${name}_complete")
        manifest { attributes["Main-Class"] = "$name.MainKt" }
        group = "build"
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        val sourcesMain = sourceSets.main.get()
        val sources = configurations["runtimeClasspath"]
            .map { if (it.isDirectory) it else zipTree(it) } + sourcesMain.output
        from(sources)
        with(tasks["jar"] as CopySpec)
    }

    // Run RenderDoc with the jar produced above
    task<Exec>("renderdoc_$name") {
        group = "examples renderdoc"
        description = "Run the $name example in RenderDoc"
        dependsOn(jar)
        commandLine(
            "renderdoccmd", "capture",
            "--wait-for-exit", "--opt-disallow-vsync", "--opt-ref-all-resources",
            "--opt-api-validation", "--opt-api-validation-unmute", "--opt-verify-buffer-access",
            "--opt-capture-callstacks", "--opt-capture-callstacks-only-actions",
            "--working-dir", System.getProperty("user.dir"),
            "--capture-file", "renderdoc/capture",
            "${System.getProperties().getProperty("java.home")}/bin/java",
            "-jar",
            jar.archiveFile.get().toString(),
        )
    }
}

createExampleTasks("01_hello_window")
createExampleTasks("02_hello_face")
createExampleTasks("03_textured_cube")
createExampleTasks("04_multiple_render_targets")
createExampleTasks("05_fxaa")

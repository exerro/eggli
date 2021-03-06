
// [LWJGL]
val lwjglNatives = run {
    val name = System.getProperty("os.name")!!
    val arch = System.getProperty("os.arch")!!
    when {
        arrayOf("Linux", "FreeBSD", "SunOS", "Unit").any { name.startsWith(it) } ->
            if (arrayOf("arm", "aarch64").any { arch.startsWith(it) })
                "natives-linux${if (arch.contains("64") || arch.startsWith("armv8")) "-arm64" else "-arm32"}"
            else
                "natives-linux"
        arrayOf("Mac OS X", "Darwin").any { name.startsWith(it) }                ->
            "natives-macos${if (arch.startsWith("aarch64")) "-arm64" else ""}"
        arrayOf("Windows").any { name.startsWith(it) }                           ->
            if (arch.contains("64"))
                "natives-windows${if (arch.startsWith("aarch64")) "-arm64" else ""}"
            else
                "natives-windows-x86"
        else -> throw Error("Unrecognized or unsupported platform. Please set \"lwjglNatives\" manually")
    }
}

plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":eggli"))
    implementation("me.exerro:lifetimes-kt:1.2.0")

    // [LWJGL]
    implementation(platform("org.lwjgl:lwjgl-bom:3.3.1-SNAPSHOT"))
    implementation("org.lwjgl", "lwjgl")
    implementation("org.lwjgl", "lwjgl-glfw")
    implementation("org.lwjgl", "lwjgl-opengl")
    implementation("org.lwjgl", "lwjgl-stb")
    runtimeOnly("org.lwjgl", "lwjgl", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-glfw", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-opengl", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-stb", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-stb", classifier = lwjglNatives)
}

////////////////////////////////////////////////////////////////////////////////

task<JavaExec>("01_hello_window") {
    group = "examples"
    description = "Run the 01_hello_window example"
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("01_hello_window.MainKt")
}

tasks.create<Jar>("jar_01_hello_window_complete") {
    archiveBaseName.set("01_hello_window_complete")
    manifest { attributes["Main-Class"] = "01_hello_window.MainKt" }
    group = "build"
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    val sourcesMain = sourceSets.main.get()
    val sources = configurations["runtimeClasspath"].map { if (it.isDirectory) it else zipTree(it) } + sourcesMain.output
    from(sources)
    with(tasks["jar"] as CopySpec)
}

////////////////////////////////////////////////////////////////////////////////

task<JavaExec>("02_hello_face") {
    group = "examples"
    description = "Run the 02_hello_face example"
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("02_hello_face.MainKt")
}

tasks.create<Jar>("jar_02_hello_face_complete") {
    archiveBaseName.set("02_hello_face_complete")
    manifest { attributes["Main-Class"] = "02_hello_face.MainKt" }
    group = "build"
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    val sourcesMain = sourceSets.main.get()
    val sources = configurations["runtimeClasspath"].map { if (it.isDirectory) it else zipTree(it) } + sourcesMain.output
    from(sources)
    with(tasks["jar"] as CopySpec)
}

////////////////////////////////////////////////////////////////////////////////

task<JavaExec>("03_textured_cube") {
    group = "examples"
    description = "Run the 03_textured_cube example"
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("03_textured_cube.MainKt")
}

tasks.create<Jar>("jar_03_textured_cube_complete") {
    archiveBaseName.set("03_textured_cube_complete")
    manifest { attributes["Main-Class"] = "03_textured_cube.MainKt" }
    group = "build"
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    val sourcesMain = sourceSets.main.get()
    val sources = configurations["runtimeClasspath"].map { if (it.isDirectory) it else zipTree(it) } + sourcesMain.output
    from(sources)
    with(tasks["jar"] as CopySpec)
}

////////////////////////////////////////////////////////////////////////////////

task<JavaExec>("04_multiple_render_targets") {
    group = "examples"
    description = "Run the 04_multiple_render_targets example"
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("04_multiple_render_targets.MainKt")
}

tasks.create<Jar>("jar_04_multiple_render_targets_complete") {
    archiveBaseName.set("04_multiple_render_targets_complete")
    manifest { attributes["Main-Class"] = "04_multiple_render_targets.MainKt" }
    group = "build"
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    val sourcesMain = sourceSets.main.get()
    val sources = configurations["runtimeClasspath"].map { if (it.isDirectory) it else zipTree(it) } + sourcesMain.output
    from(sources)
    with(tasks["jar"] as CopySpec)
}

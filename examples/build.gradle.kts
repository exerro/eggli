
plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":eggli"))
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

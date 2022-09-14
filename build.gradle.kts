import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val lwjglVersion = "3.3.2-SNAPSHOT"
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

allprojects {
    val lwjglApi by configurations.creating
    val lwjglRuntimeOnly by configurations.creating

    dependencies {
        lwjglApi(platform("org.lwjgl:lwjgl-bom:$lwjglVersion"))
        lwjglApi("org.lwjgl", "lwjgl")
        lwjglApi("org.lwjgl", "lwjgl-glfw")
        lwjglApi("org.lwjgl", "lwjgl-opengl")
        lwjglApi("org.lwjgl", "lwjgl-stb")
        lwjglRuntimeOnly("org.lwjgl", "lwjgl", classifier = lwjglNatives)
        lwjglRuntimeOnly("org.lwjgl", "lwjgl-glfw", classifier = lwjglNatives)
        lwjglRuntimeOnly("org.lwjgl", "lwjgl-opengl", classifier = lwjglNatives)
        lwjglRuntimeOnly("org.lwjgl", "lwjgl-stb", classifier = lwjglNatives)
        lwjglRuntimeOnly("org.lwjgl", "lwjgl-stb", classifier = lwjglNatives)
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
        kotlinOptions.freeCompilerArgs += "-Xcontext-receivers"
        kotlinOptions.freeCompilerArgs += "-Xskip-prerelease-check"
        kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.contracts.ExperimentalContracts"
        kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.time.ExperimentalTime"
        kotlinOptions.freeCompilerArgs += "-language-version"
        kotlinOptions.freeCompilerArgs += "1.6"
    }
}

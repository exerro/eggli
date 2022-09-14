rootProject.name = "eggli"
val eggliVersion = "0.1.2"

val extra = (gradle as ExtensionAware).extra
extra["eggliVersion"] = eggliVersion

pluginManagement {
    val kotlinVersion = "1.7.20-RC"
    val kspVersion = "$kotlinVersion-1.0.6"

    plugins {
        kotlin("jvm") version kotlinVersion
        id("com.google.devtools.ksp") version kspVersion
    }

    repositories {
        gradlePluginPortal()
        google()
    }
}

dependencyResolutionManagement {
    val kotlinVersion = "1.7.20-RC"
    val kspVersion = "$kotlinVersion-1.0.6"

    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        google()
        mavenCentral()
        mavenLocal()
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots/") }
    }

    versionCatalogs {
        create("libs") {
//            library("ksp", "com.google.devtools.ksp:symbol-processing-api:$kspVersion")
        }
    }
}

include(":eggli", ":egglix", ":eggli-gen", ":eggli-examples")
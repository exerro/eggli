
val eggliVersion = "0.2.0"

plugins {
    kotlin("jvm")
    `maven-publish`
    id("com.google.devtools.ksp") version "1.7.20-1.0.6"
}

dependencies {
    ksp(project(":eggli:codegen"))

    implementation(kotlin("stdlib"))
    api("me.exerro:lifetimes-kt:1.2.1")
}

configurations {
    api.get().extendsFrom(configurations.getByName("lwjglApi"))
    runtimeOnly.get().extendsFrom(configurations.getByName("lwjglRuntimeOnly"))
}

////////////////////////////////////////////////////////////////////////////////

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "me.exerro"
            artifactId = "eggli"
            version = eggliVersion

            from(components["java"])
        }
    }
}

////////////////////////////////////////////////////////////////////////////////

ksp {
    // arg("key", "value")
}

kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
    sourceSets.test {
        kotlin.srcDir("build/generated/ksp/test/kotlin")
    }
}

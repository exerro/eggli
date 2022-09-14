
plugins {
    kotlin("jvm")
    `maven-publish`
    id("com.google.devtools.ksp")
}

dependencies {
    ksp(project(":eggli-gen"))

    implementation(kotlin("stdlib"))
    api("me.exerro:lifetimes-kt:1.2.0")
}

configurations {
    api.get().extendsFrom(configurations.getByName("lwjglApi"))
    runtimeOnly.get().extendsFrom(configurations.getByName("lwjglRuntimeOnly"))
}

////////////////////////////////////////////////////////////////////////////////

publishing {
    publications {
        create<MavenPublication>("maven") {
            val eggliVersion: String by (gradle as ExtensionAware).extra

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

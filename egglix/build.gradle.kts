
plugins {
    kotlin("jvm")
    `maven-publish`
}

dependencies {
    implementation(project(":eggli"))
    implementation(kotlin("stdlib"))
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
            artifactId = "egglix"
            version = eggliVersion

            from(components["java"])
        }
    }
}

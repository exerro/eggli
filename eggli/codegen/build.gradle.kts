
plugins {
    kotlin("jvm")
}

dependencies {
    implementation("com.google.devtools.ksp:symbol-processing-api:1.8.10-1.0.9")
    implementation("com.squareup:kotlinpoet:1.12.0")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.freeCompilerArgs += "-opt-in=com.google.devtools.ksp.KspExperimental"
}

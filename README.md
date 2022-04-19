<h1 align="center">
  eggli
</h1>

<p align="center">
  <a href="https://jitpack.io/#exerro/eggli"><img src="https://jitpack.io/v/exerro/eggli.svg" alt="JitPack badge"/></a>
</p>

A template for kotlin projects.

## Installation

Check out the [releases](https://github.com/exerro/eggli/releases), or
using a build system...

### Gradle (`build.gradle.kts`)

```kotlin
repositories {
    // ...
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("me.exerro:eggli:1.0.0")
}
```

### Maven

```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependency>
  <groupId>me.exerro</groupId>
  <artifactId>eggli</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Updating this template

* Change `eggli` in `settings.gradle.kts`
* Change `eggli` in `build.gradle.kts`
* Change `eggli` in this README.

## Testing the build before release

    ./gradlew clean && ./gradlew build && ./gradlew build publishToMavenLocal

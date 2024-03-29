<h1 align="center">
  eggli
</h1>

<p align="center">
  <a href="https://jitpack.io/#exerro/eggli"><img src="https://jitpack.io/v/exerro/eggli.svg" alt="JitPack badge"/></a>
</p>

Eggli is a lightweight wrapper around OpenGL, aiming to make graphics a bit
nicer and a bit more Kotlin-like.

Eggli also adds some higher level features as extensions on top of the core
functionality, for example an FXAA implementation.

### Utility functions

The package `me.exerro.egglix` contains a whole load of functionality which
builds on top of the rest of the library. It can be used as reference when
implementing similar functionality, or used to simplify common tasks. For
example, there's a method to produce a cube mesh complete with UVs and per-face
colours, as well as a helper that simplifies shader compilation.

As well as simpler utilities, this package adds things like an FXAA
implementation.

### GL contexts

OpenGL is single threaded. To avoid various related multithreading issues, Eggli
exposes a `GLWorker` interface which lets work be submitted to run on a
dedicated OpenGL thread. All the Eggli functions which call  OpenGL require a
`GLContext` in scope, which is provided by `GLWorker`. Values that should be
evaluated on this thread can be wrapped in a `GL` instance, e.g. `GL<Int>`.
`GLWorker` also lets you `evaluate` `GL<T>`s to get values back from the OpenGL
thread.

### Enum narrowing

The LWJGL bindings, as well as the OpenGL spec, use a mysterious `GLenum` type
all over the place. This means you can pass in `GL_RED` to something expecting
a framebuffer name. Eggli uses a code generator to create objects for each enum
member that inherit from each interface it belongs to, for example `GL_RED` is
both a `GLTextureImageFormat` and a`GLTextureParameterSwizzleValue`. This helps
avoid runtime errors from passing in invalid values, and also helps
experimentation and discovery - you can see what your IDE suggests from
`GLTextureImageFormat.___`.

### Lifetimes for object allocation

In OpenGL, objects are explicitly allocated and destroyed. In Eggli, objects are
allocated within a `Lifetime`, which automatically frees the objects when ended.
The [lifetime library](https://github.com/exerro/lifetimes-kt) offers a lot of
functionality which I won't go into here, but this feature allows you to worry
less about passing around handles to be destroyed later and focus on the code
you want to write.

Objects allocated are wrapped in a `GLResource<T>` which lets you safely access
the value within (ensuring it's not been destroyed) and explicitly destroy it if
required.

## Repository structure

* `eggli` - library source code
* `eggli/codegen` - code generator to write GLenum content
* `examples` - example code

### Examples

Please use the
[examples](https://github.com/exerro/eggli/tree/main/examples/src/main/kotlin)
as a reference for how to do things, both with Eggli and  just in general with
OpenGL.

![Screenshot of example showing a textured cube.](examples/src/main/kotlin/03_textured_cube/screenshot.png)

## Installation

Check out the [releases](https://github.com/exerro/eggli/releases), or
using a build system...

Note: There's an issue right now where you need the lifetimes library and LWJGL
natives.

### Gradle (`build.gradle.kts`)

```kotlin
repositories {
    // ...
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    // implementation("me.exerro:eggli:0.2.0")
    // while in development, use this instead:
    implementation("me.exerro:eggli:main-SNAPSHOT")
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
  <!--<version>0.2.0</version>-->
  <!--while in development, use this instead:-->
  <version>main-SNAPSHOT</version>
</dependency>
```

TODO: VM option `-javaagent:lib/lwjglx-debug-1.0.0.jar` seems to do debugging
stuff

## Debugging

A debugging interface was previously included in this repo, until I found
[RenderDoc](https://renderdoc.org/). RenderDoc is the most insanely useful and
incredible bit of software for graphics development. It lets you step through
every single draw call, see all the parameters, see texture and buffers on the
GPU after every call, dynamically edit shader code. It's crazy. Here's an
example showing it mid-draw - you can see the screen mid-frame before the normal
and depth textures have been drawn, and arbitrarily navigate through the
annotated frame.

![Example RenderDoc event browser listing](img/renderdoc_event_browser.png)
![Example RenderDoc texture viewer](img/renderdoc_texture_viewer.png)

Eggli additionally uses some features used by RenderDoc, notably object names
and marker regions. When creating objects, pass a name to see it annotated in
RenderDoc. Additionally, use `glPushDebugGroupKHR` and `glPopDebugGroupKHR` to
mark logical regions of draw calls and see them grouped within RenderDoc. You
can see this being used above, e.g. "Screen draw pass".

To use RenderDoc, you need to pass an executable and parameters. See
[this tutorial](https://lwjglgamedev.gitbooks.io/3d-game-development-with-lwjgl/content/appendixa/appendixa.html).
One way that works is creating a "fat jar" with all your main code and passing
that to `java` as shown in the tutorial. See `examples/build.gradle.kts` for
an implementation of both the fat jar task as well as a task to run RenderDoc
with that jar.
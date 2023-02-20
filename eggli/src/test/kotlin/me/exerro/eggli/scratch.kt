package me.exerro.eggli

import org.lwjgl.BufferUtils
import org.lwjgl.glfw.GLFW
import org.lwjgl.stb.STBImageWrite
import org.lwjgl.stb.STBTTFontinfo
import org.lwjgl.stb.STBTruetype
import kotlin.math.round

fun fontTest() {
    val fontResource = GLWorker::class.java
        .getResourceAsStream("/karmilla/Karmilla-Regular.ttf")
        ?.readAllBytes()
        ?: error("Failed to load Karmilla font")
    val fontContent = BufferUtils.createByteBuffer(fontResource.size)
    fontContent.put(fontResource)
    fontContent.flip()
    val fontInfo = STBTTFontinfo(fontContent)

    val fontHeight = 64f
    val ascentBuffer = IntArray(1)
    val descentBuffer = IntArray(1)
    val lineGapBuffer = IntArray(1)
    STBTruetype.stbtt_InitFont(fontInfo, fontContent)
    val scale = STBTruetype.stbtt_ScaleForPixelHeight(fontInfo, fontHeight)
    STBTruetype.stbtt_GetFontVMetrics(fontInfo, ascentBuffer, descentBuffer, lineGapBuffer)
    val word = "the quick brown fox"
    val ascent = round(scale * ascentBuffer[0]).toInt()
    val descent = round(scale * descentBuffer[0]).toInt()
    val lineGap = round(scale * lineGapBuffer[0]).toInt()
    var x = 0
    val bitmapWidth = 512
    val bitmapHeight = 128
    val bitmap = BufferUtils.createByteBuffer(bitmapWidth * bitmapHeight)

    val advanceWidthBuffer = IntArray(1)
    val leftSideBearingBuffer = IntArray(1)
    val cx0Buffer = IntArray(1)
    val cy0Buffer = IntArray(1)
    val cx1Buffer = IntArray(1)
    val cy1Buffer = IntArray(1)

    for (i in word.indices) {
        STBTruetype.stbtt_GetCodepointHMetrics(fontInfo, word.codePointAt(i), advanceWidthBuffer, leftSideBearingBuffer)
        STBTruetype.stbtt_GetCodepointBitmapBox(fontInfo, word.codePointAt(i), scale, scale, cx0Buffer, cy0Buffer, cx1Buffer, cy1Buffer)

        val y = ascent + cy0Buffer[0]
        val byteOffset = x + round(leftSideBearingBuffer[0] * scale).toInt() + (y * bitmapWidth)

        val target = bitmap.slice(byteOffset, bitmapWidth * bitmapHeight - byteOffset)
        STBTruetype.stbtt_MakeCodepointBitmap(fontInfo, target, cx1Buffer[0] - cx0Buffer[0], cy1Buffer[0] - cy0Buffer[0], bitmapWidth, scale, scale, word.codePointAt(i))

        x += round(advanceWidthBuffer[0] * scale).toInt()

        if (i < word.lastIndex) {
            val kern = STBTruetype.stbtt_GetCodepointKernAdvance(fontInfo, word.codePointAt(i), word.codePointAt(i + 1));
            x += round(kern * scale).toInt()
        }
    }

    STBImageWrite.stbi_write_png("out.png", bitmapWidth, bitmapHeight, 1, bitmap, bitmapWidth)
}

fun main() {
    GLFW.glfwInit()

    fontTest()

//    withLifetime {
//        val (windowId, worker) = createGLFWWindowWithWorker(title = "Eggli Hello Window Example")
//
//        worker.runLater {
//            glClearColor(0.1f, 0.4f, 0.7f)
//        }
//
//        val renderLoopHandle = createGLFWRenderLoop(windowId, worker) {
//            glClear(GL_COLOR_BUFFER_BIT)
//        }
//
//        while (!GLFW.glfwWindowShouldClose(windowId)) {
//            GLFW.glfwWaitEvents()
//        }
//
//        renderLoopHandle.stopBlocking()
//    }
}

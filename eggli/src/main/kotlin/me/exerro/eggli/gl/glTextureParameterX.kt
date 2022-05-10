package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.enum.*
import me.exerro.eggli.types.GLTexture
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext, GLDebugger.Context)
@JvmName("glTextureParameterSwizzle")
fun <Swizzle, SwizzleValue> glTextureParameter(
    texture: GLTexture,
    swizzle: Swizzle,
    value: SwizzleValue,
) where Swizzle: GLTextureParameter.Swizzle, Swizzle: GLenum, SwizzleValue: GLTextureSwizzleValue, SwizzleValue: GLenum {
    glLog(GLDebugger.LogAction.StateChanged, GLDebugger.LogEntity.Texture, "Setting swizzle ($swizzle) for texture $texture to $value")
    GL46C.glTextureParameteri(texture.get(), swizzle.glValue, value.glValue)
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun <Swizzle, SwizzleValue> glTextureParameter(
    texture: GLTexture,
    filter: GL_TEXTURE_MIN_FILTER,
    value: SwizzleValue,
) where Swizzle: GLTextureParameter.Swizzle, Swizzle: GLenum, SwizzleValue: GLTextureSwizzleValue, SwizzleValue: GLenum {
    TODO()
}

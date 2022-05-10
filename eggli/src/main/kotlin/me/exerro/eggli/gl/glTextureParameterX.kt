package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.GLDebugger
import me.exerro.eggli.enum.*
import me.exerro.eggli.types.GLTexture
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext, GLDebugger.Context)
@JvmName("glTextureParameterSwizzle")
fun glTextureParameter(
    texture: GLTexture,
    swizzle: GLTextureParameterSwizzle,
    value: GLTextureParameterSwizzleValue,
) {
    glLog(GLDebugger.LogAction.StateChanged, GLDebugger.LogEntity.Texture, "Setting swizzle ($swizzle) for texture $texture to $value")
    GL46C.glTextureParameteri(texture.get(), swizzle.glValue, value.glValue)
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
@JvmName("glTextureParameterSwizzleRGBA")
fun glTextureParameter(
    texture: GLTexture,
    swizzle: GL_TEXTURE_SWIZZLE_RGBA,
    red: GLTextureParameterSwizzleValue,
    green: GLTextureParameterSwizzleValue,
    blue: GLTextureParameterSwizzleValue,
    alpha: GLTextureParameterSwizzleValue,
) {
    glLog(GLDebugger.LogAction.StateChanged, GLDebugger.LogEntity.Texture, "Setting swizzle for texture $texture to ($red, $green, $blue, $alpha)")
    GL46C.glTextureParameteriv(texture.get(), swizzle.glValue, listOf(red, green, blue, alpha).map { it.glValue } .toIntArray())
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
@JvmName("glTextureParameterMagFilter")
fun glTextureParameter(
    texture: GLTexture,
    filter: GL_TEXTURE_MAG_FILTER,
    value: GLTextureParameterMagFilterValue,
) {
    glLog(GLDebugger.LogAction.StateChanged, GLDebugger.LogEntity.Texture, "Setting mag filter for texture $texture to $value")
    GL46C.glTextureParameteri(texture.get(), filter.glValue, value.glValue)
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
@JvmName("glTextureParameterMinFilter")
fun glTextureParameter(
    texture: GLTexture,
    filter: GL_TEXTURE_MIN_FILTER,
    value: GLTextureParameterMinFilterValue,
) {
    glLog(GLDebugger.LogAction.StateChanged, GLDebugger.LogEntity.Texture, "Setting min filter for texture $texture to $value")
    GL46C.glTextureParameteri(texture.get(), filter.glValue, value.glValue)
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
@JvmName("glTextureParameterWrap")
fun glTextureParameter(
    texture: GLTexture,
    wrap: GLTextureParameterWrap,
    value: GLTextureParameterWrapValue,
) {
    glLog(GLDebugger.LogAction.StateChanged, GLDebugger.LogEntity.Texture, "Setting wrap ($wrap) for texture $texture to $value")
    GL46C.glTextureParameteri(texture.get(), wrap.glValue, value.glValue)
    glCheckForErrors()
}

// https://www.khronos.org/registry/OpenGL-Refpages/gl4/html/glTexParameter.xhtml
// TODO: GL_DEPTH_STENCIL_TEXTURE_MODE
// TODO: GL_TEXTURE_BASE_LEVEL
// TODO: GL_TEXTURE_COMPARE_FUNC
// TODO: GL_TEXTURE_COMPARE_MODE
// TODO: GL_TEXTURE_LOD_BIAS
// TODO: GL_TEXTURE_MIN_LOD
// TODO: GL_TEXTURE_MAX_LOD
// TODO: GL_TEXTURE_MAX_LEVEL
// TODO: GL_TEXTURE_BORDER_COLOR

/** TODO */
context (GLContext, GLDebugger.Context)
fun glTextureParameteri(
    texture: GLTexture,
    parameter: GLTextureParameterI,
    value: Int,
) {
    glLog(GLDebugger.LogAction.StateChanged, GLDebugger.LogEntity.Texture, "Setting parameter $parameter for texture $texture to $value")
    GL46C.glTextureParameteri(texture.get(), parameter.glValue, value)
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun glTextureParameterf(
    texture: GLTexture,
    parameter: GLTextureParameter,
    value: Float,
) {
    glLog(GLDebugger.LogAction.StateChanged, GLDebugger.LogEntity.Texture, "Setting parameter $parameter for texture $texture to $value")
    GL46C.glTextureParameterf(texture.get(), parameter.glValue, value)
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun glTextureParameteriv(
    texture: GLTexture,
    parameter: GLTextureParameterI,
    value: IntArray,
) {
    glLog(GLDebugger.LogAction.StateChanged, GLDebugger.LogEntity.Texture, "Setting parameter $parameter for texture $texture to $value")
    GL46C.glTextureParameteriv(texture.get(), parameter.glValue, value)
    glCheckForErrors()
}

/** TODO */
context (GLContext, GLDebugger.Context)
fun glTextureParameterfv(
    texture: GLTexture,
    parameter: GLTextureParameter,
    value: FloatArray,
) {
    glLog(GLDebugger.LogAction.StateChanged, GLDebugger.LogEntity.Texture, "Setting parameter $parameter for texture $texture to $value")
    GL46C.glTextureParameterfv(texture.get(), parameter.glValue, value)
    glCheckForErrors()
}

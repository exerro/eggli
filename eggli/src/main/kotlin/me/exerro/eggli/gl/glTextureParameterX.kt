package me.exerro.eggli.gl

import me.exerro.eggli.GLContext
import me.exerro.eggli.enum.*
import me.exerro.eggli.types.GLTexture
import org.lwjgl.opengl.GL46C

/** TODO */
context (GLContext)
@JvmName("glTextureParameterSwizzle")
fun glTextureParameter(
    texture: GLTexture,
    swizzle: GLTextureParameterSwizzle,
    value: GLTextureParameterSwizzleValue,
) {
    GL46C.glTextureParameteri(texture.get(), swizzle.glValue, value.glValue)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
@JvmName("glTextureParameterSwizzleRGBA")
fun glTextureParameter(
    texture: GLTexture,
    swizzle: GL_TEXTURE_SWIZZLE_RGBA,
    red: GLTextureParameterSwizzleValue,
    green: GLTextureParameterSwizzleValue,
    blue: GLTextureParameterSwizzleValue,
    alpha: GLTextureParameterSwizzleValue,
) {
    GL46C.glTextureParameteriv(texture.get(), swizzle.glValue, listOf(red, green, blue, alpha).map { it.glValue } .toIntArray())
    glCheckForErrors()
}

/** TODO */
context (GLContext)
@JvmName("glTextureParameterMagFilter")
fun glTextureParameter(
    texture: GLTexture,
    filter: GL_TEXTURE_MAG_FILTER,
    value: GLTextureParameterMagFilterValue,
) {
    GL46C.glTextureParameteri(texture.get(), filter.glValue, value.glValue)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
@JvmName("glTextureParameterMinFilter")
fun glTextureParameter(
    texture: GLTexture,
    filter: GL_TEXTURE_MIN_FILTER,
    value: GLTextureParameterMinFilterValue,
) {
    GL46C.glTextureParameteri(texture.get(), filter.glValue, value.glValue)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
@JvmName("glTextureParameterWrap")
fun glTextureParameter(
    texture: GLTexture,
    wrap: GLTextureParameterWrap,
    value: GLTextureParameterWrapValue,
) {
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
context (GLContext)
fun glTextureParameteri(
    texture: GLTexture,
    parameter: GLTextureParameterI,
    value: Int,
) {
    GL46C.glTextureParameteri(texture.get(), parameter.glValue, value)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
fun glTextureParameterf(
    texture: GLTexture,
    parameter: GLTextureParameter,
    value: Float,
) {
    GL46C.glTextureParameterf(texture.get(), parameter.glValue, value)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
fun glTextureParameteriv(
    texture: GLTexture,
    parameter: GLTextureParameterI,
    value: IntArray,
) {
    GL46C.glTextureParameteriv(texture.get(), parameter.glValue, value)
    glCheckForErrors()
}

/** TODO */
context (GLContext)
fun glTextureParameterfv(
    texture: GLTexture,
    parameter: GLTextureParameter,
    value: FloatArray,
) {
    GL46C.glTextureParameterfv(texture.get(), parameter.glValue, value)
    glCheckForErrors()
}

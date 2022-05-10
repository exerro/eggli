package me.exerro.eggli.gen

data class GLenumDescriptor(
    val name: String,
    val docString: String,
    val members: List<Member>,
) {
    data class Member(
        val name: String,
        val docString: String,
    ) {
        val kotlinName by lazy {
            upperSnakeCaseToPascalCase(name.replaceFirst("GL_", ""))
        }

        override fun equals(other: Any?) = other is Member && name == other.name
        override fun hashCode() = name.hashCode()
    }
}

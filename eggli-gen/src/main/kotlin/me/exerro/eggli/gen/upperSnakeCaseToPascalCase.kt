package me.exerro.eggli.gen

fun upperSnakeCaseToPascalCase(s: String): String {
    val parts = s.split('_')

    return parts.joinToString("") { part ->
        part.first().uppercase() + part.drop(1).lowercase()
    }
}

package com.tepe.pokemontest.model.dto

data class Pokemon(
    val name: String,
    val image: String?,
    val _number: Int,
    val description: String? = null,
) {

    val number: String get() {
        val str = _number.toString()
        return "#${getZeros(str.length)}$str"
    }

    private fun getZeros(length: Int): String {
        return when (length) {
            1 -> "00"
            2 -> "0"
            else -> ""
        }
    }
}

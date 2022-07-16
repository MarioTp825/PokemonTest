package com.tepe.pokemontest.model.pojo


import com.google.gson.annotations.SerializedName

data class PokemonCollectionPage(
    val count: Int? = null,
    val next: String? = null,
    val previous: String? = null,
    val results: List<Result?>? = null
) {
    data class Result(
        val name: String? = null,
        val url: String? = null
    )
}
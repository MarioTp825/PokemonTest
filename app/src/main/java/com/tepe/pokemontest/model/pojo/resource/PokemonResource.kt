package com.tepe.pokemontest.model.pojo.resource


import com.google.gson.annotations.SerializedName

data class PokemonResource(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("sprites")
    val sprites: Sprites? = Sprites(),
)
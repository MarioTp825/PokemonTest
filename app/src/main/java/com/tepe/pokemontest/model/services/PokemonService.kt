package com.tepe.pokemontest.model.services

import com.tepe.pokemontest.model.pojo.PokemonCollectionPage
import com.tepe.pokemontest.model.pojo.resource.PokemonResource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {
    @GET("pokemon/{id}")
    fun singlePokemon(
        @Path("id") id: String
    ) : Call<PokemonResource?>?

    @GET("pokemon")
    fun pokemonResource(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ) : Call<PokemonCollectionPage?>?
}
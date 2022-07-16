package com.tepe.pokemontest.model.implementation

import com.tepe.pokemontest.BuildConfig
import com.tepe.pokemontest.model.dto.Pokemon
import com.tepe.pokemontest.model.pojo.resource.PokemonResource
import com.tepe.pokemontest.model.repository.DashboardRepository
import com.tepe.pokemontest.model.services.PokemonService
import com.tepe.pokemontest.utils.DefaultRetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonSingleRepositoryImpl(
    override val mainRepository: DashboardRepository.PokemonDashboardRepository<Pokemon>
) : Callback<PokemonResource?>, DashboardRepository.PokemonSingleRepository<Pokemon> {
    var id: String = ""

    private val service by lazy {
        DefaultRetrofitFactory.newInstance(
            baseUrl = BuildConfig.BASE_URL,
            serviceClass = PokemonService::class.java
        )
    }

    override fun onResponse(call: Call<PokemonResource?>, response: Response<PokemonResource?>) {
        if (!response.isSuccessful) {
            addFailure(
                msg = "error ${response.code()}: ${response.message()}"
            )
            return
        }
        response.body()?.let { pk ->
            mainRepository.enqueuePokemon(
                pokemon = Pokemon(
                    name = pk.name.na(),
                    _number = pk.id.na(),
                    image = pk.sprites?.frontDefault,
                ),
                message = response.message()
            )
        } ?: addFailure(msg = "Error when trying to get response of $id")

    }

    override fun onFailure(call: Call<PokemonResource?>, t: Throwable) = addFailure(t.message)

    private fun addFailure(msg: String?) {
        mainRepository.enqueuePokemon(
            pokemon = null,
            message = msg
        )
    }

    override fun loadData(id: String) {
        this.id = id
        service.singlePokemon(
            id = id
        )?.enqueue(this)
    }

    //If the property was not available, returns N/A
    fun String?.na() = this ?: "N/A"
    fun Int?.na() = this ?: -1
}
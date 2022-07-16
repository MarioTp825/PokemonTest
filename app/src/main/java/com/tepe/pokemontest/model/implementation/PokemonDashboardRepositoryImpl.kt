package com.tepe.pokemontest.model.implementation

import com.tepe.pokemontest.BuildConfig
import com.tepe.pokemontest.model.dto.Pokemon
import com.tepe.pokemontest.model.pojo.PokemonCollectionPage
import com.tepe.pokemontest.model.repository.DashboardRepository
import com.tepe.pokemontest.model.services.PokemonService
import com.tepe.pokemontest.presenter.implementation.DashboardInteractor
import com.tepe.pokemontest.utils.DefaultRetrofitFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class PokemonDashboardRepositoryImpl(
    override val interactor: DashboardInteractor.PokemonDashboardInteractor
) : DashboardRepository.PokemonDashboardRepository<Pokemon>, Callback<PokemonCollectionPage?> {
    private val errorQueue = mutableListOf<String>()
    private val resourceCollection = Stack<PokemonCollectionPage.Result>()

    private val singleRepository by lazy {
        PokemonSingleRepositoryImpl(
            mainRepository = this
        )
    }
    private val _pokemonCollection = mutableListOf<Pokemon>()
    private val itemsPerPage = 10
    private var currentPage = 0

    private val service by lazy {
        DefaultRetrofitFactory.newInstance(
            baseUrl = BuildConfig.BASE_URL,
            serviceClass = PokemonService::class.java
        )
    }

    override fun loadData() {
        service.pokemonResource(
            limit = itemsPerPage,
            offset = itemsPerPage * currentPage
        )?.enqueue(this)
    }

    override fun onResponse(
        call: Call<PokemonCollectionPage?>,
        response: Response<PokemonCollectionPage?>
    ) {
        response.body()?.let {
            currentPage++
            CoroutineScope(Dispatchers.IO).launch {
                it.results?.forEach { rs ->
                    rs?.let {
                        resourceCollection.push(it)
                    }
                }
                startReading()
            }
        } ?: interactor.showError(message = "Error al obtener los datos")

    }

    private fun startReading() {
        if(resourceCollection.isEmpty()) {
            interactor.update()
            return
        }
        resourceCollection.pop().let { pk ->
            pk?.url
                ?.replace(BuildConfig.BASE_URL, "")
                ?.replace("pokemon","")
                ?.replace("/", "")
                ?.let { id -> singleRepository.loadData(id = id) }
        }
    }

    override fun onFailure(call: Call<PokemonCollectionPage?>, t: Throwable) {
        interactor.showError(message = t.message)
    }

    override fun enqueuePokemon(pokemon: Pokemon?, message: String?) {
        pokemon?.let { pk ->
            _pokemonCollection.add(pk)
        }?: message?.let {
            errorQueue.add(it)
        }
        startReading()
    }

    override val pokemonCollection: MutableList<Pokemon>
        get() = _pokemonCollection

}

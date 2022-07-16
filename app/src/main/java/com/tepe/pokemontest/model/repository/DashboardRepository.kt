package com.tepe.pokemontest.model.repository

import com.tepe.pokemontest.presenter.implementation.DashboardInteractor

sealed interface DashboardRepository<T> {

    fun loadData()

    interface PokemonDashboardRepository<T>: DashboardRepository<T> {
        val pokemonCollection: MutableList<T>
        val interactor: DashboardInteractor.PokemonDashboardInteractor

        fun enqueuePokemon(pokemon: T?, message: String? = null)
    }

    interface PokemonSingleRepository<T> {
        val mainRepository: PokemonDashboardRepository<T>
        fun loadData(id: String)
    }
}
package com.tepe.pokemontest.presenter.implementation

import com.tepe.pokemontest.model.dto.Pokemon
import com.tepe.pokemontest.view.abstraction.DashboardView
import com.tepe.pokemontest.view.adapter.PokemonCardAdapter

sealed interface DashboardInteractor<T> {

    fun loadData()

    fun showError(message: String?)

    fun updateData(data: List<T>?)
    fun update()

    interface PokemonDashboardInteractor: DashboardInteractor<Pokemon> {
        val view: DashboardView.PokemonDashboardView
        val adapter: PokemonCardAdapter

        override fun updateData(data: List<Pokemon>?)
    }
}
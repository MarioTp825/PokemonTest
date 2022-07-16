package com.tepe.pokemontest.view.abstraction

import com.tepe.pokemontest.presenter.implementation.DashboardInteractor.PokemonDashboardInteractor

sealed interface DashboardView {

    fun showErrorDialog(message: String)

    interface PokemonDashboardView: DashboardView {

        val interactor: PokemonDashboardInteractor
    }
}
package com.tepe.pokemontest.presenter.interactor

import com.tepe.pokemontest.model.dto.Pokemon
import com.tepe.pokemontest.model.implementation.PokemonDashboardRepositoryImpl
import com.tepe.pokemontest.model.repository.DashboardRepository.PokemonDashboardRepository
import com.tepe.pokemontest.presenter.implementation.DashboardInteractor.PokemonDashboardInteractor
import com.tepe.pokemontest.view.abstraction.DashboardView
import com.tepe.pokemontest.view.adapter.PokemonCardAdapter

class PokemonDashboardInteractorImpl(override val view: DashboardView.PokemonDashboardView) :
    PokemonDashboardInteractor {

    private val model: PokemonDashboardRepository<Pokemon> = PokemonDashboardRepositoryImpl(
        interactor = this
    )

    private val _adapter by lazy {
        PokemonCardAdapter (
            pokemonCollection = model.pokemonCollection,
            loadData = { loadData() }
        )
    }

    override val adapter get() = _adapter


    override fun loadData() {
        model.loadData()
    }

    override fun showError(message: String?) {
        view.showErrorDialog (
            message = message ?: "Error de red desconocido"
        )
    }

    override fun update() {
        adapter.update()
    }

    override fun updateData(data: List<Pokemon>?) {
        data?.let {
            adapter.addItems(it)
        } ?: view.showErrorDialog (
            message = "No se recibió respuesta del servidor"
        )
    }
}
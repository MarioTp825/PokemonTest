package com.tepe.pokemontest.presenter.interactor

import com.tepe.pokemontest.presenter.implementation.DashboardInteractor
import com.tepe.pokemontest.presenter.implementation.DialogInteractor
import com.tepe.pokemontest.view.abstraction.DialogView

class ErrorDialogInteractorImpl(
    private val view: DialogView,
    private val interactor: DashboardInteractor.PokemonDashboardInteractor
): DialogInteractor.ErrorDialogInteractor {
    override fun onDismiss() {
        interactor.loadData()
    }
}
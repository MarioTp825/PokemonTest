package com.tepe.pokemontest.presenter.implementation

sealed interface DialogInteractor {

    fun onDismiss()

    interface ErrorDialogInteractor: DialogInteractor {

    }
}
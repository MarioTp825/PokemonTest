package com.tepe.pokemontest.view.implementation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.tepe.pokemontest.databinding.FragmentErrorBinding
import com.tepe.pokemontest.presenter.implementation.DashboardInteractor
import com.tepe.pokemontest.presenter.implementation.DialogInteractor.ErrorDialogInteractor
import com.tepe.pokemontest.presenter.interactor.ErrorDialogInteractorImpl
import com.tepe.pokemontest.view.abstraction.DialogView

class ErrorDialogFragment : DialogFragment(), DialogView.ErrorDialogView {
    private val interactor by lazy {
        ErrorDialogInteractorImpl(
            view = this,
            interactor = pokemonInteractor!!
        )
    }

    private var _binding: FragmentErrorBinding? = null
    private val binding get() = _binding!!

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT,
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentErrorBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnTryAgain.setOnClickListener {
            interactor?.onDismiss()
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private var pokemonInteractor: DashboardInteractor.PokemonDashboardInteractor? = null

        fun newInstance(interactor: DashboardInteractor.PokemonDashboardInteractor) = ErrorDialogFragment().also {
            pokemonInteractor = interactor
        }
    }
}
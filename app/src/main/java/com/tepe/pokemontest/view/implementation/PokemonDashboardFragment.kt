package com.tepe.pokemontest.view.implementation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.tepe.pokemontest.databinding.FragmentPokemonDashboardBinding
import com.tepe.pokemontest.presenter.implementation.DashboardInteractor
import com.tepe.pokemontest.presenter.interactor.PokemonDashboardInteractorImpl
import com.tepe.pokemontest.view.abstraction.DashboardView.PokemonDashboardView

class PokemonDashboardFragment : Fragment(), PokemonDashboardView {

    override val interactor: DashboardInteractor.PokemonDashboardInteractor =
        PokemonDashboardInteractorImpl(view = this)

    private val dialog by lazy { ErrorDialogFragment.newInstance(
        interactor = interactor
    ) }

    override fun showErrorDialog(message: String) {

        dialog.show(parentFragmentManager,"error_pokemon")

    }

    private var _binding: FragmentPokemonDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonDashboardBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPokemonList.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = interactor.adapter
        }
        interactor.loadData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = PokemonDashboardFragment()
    }
}
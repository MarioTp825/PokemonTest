package com.tepe.pokemontest

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.tepe.pokemontest.databinding.ActivityMainBinding
import com.tepe.pokemontest.view.implementation.PokemonDashboardFragment

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpFragment()
    }

    private fun setUpFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fMainContainer,
                PokemonDashboardFragment.newInstance()
            ).commit()
    }
}
package com.example.tatafood.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.tatafood.R
import com.example.tatafood.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigation=binding.bottomNavigation
        val findNavigation=Navigation.findNavController(this, R.id.hostFragment)
        NavigationUI.setupWithNavController(bottomNavigation,findNavigation)
    }
}
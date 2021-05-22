package com.rightside.hackaton.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.rightside.hackaton.R
import com.rightside.hackaton.databinding.ActivityHomeBinding
import com.rightside.hackaton.extensions.invisibleUI
import com.rightside.hackaton.extensions.visibleUI

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id) {
                R.id.loginFragment -> binding.bottomNavigationView.invisibleUI()
                R.id.feedFragment -> binding.bottomNavigationView.visibleUI()
                R.id.actionDetailsFragment -> binding.bottomNavigationView.invisibleUI()
            }
        }
    }
}
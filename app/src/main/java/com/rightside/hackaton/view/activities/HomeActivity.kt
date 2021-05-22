package com.rightside.hackaton.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
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
        setSupportActionBar(binding.toolbar.tool)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id) {
                R.id.loginFragment -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    binding.bottomNavigationView.invisibleUI() }

                R.id.feedFragment -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    binding.bottomNavigationView.visibleUI() }
                R.id.actionDetailsFragment -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    binding.bottomNavigationView.invisibleUI() }
                R.id.producerDetailsFragment -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    binding.bottomNavigationView.invisibleUI() }
                R.id.producerFragment -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    binding.bottomNavigationView.visibleUI()
                }
                R.id.profileFragment -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    binding.bottomNavigationView.visibleUI()
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
package com.anesabml.zalando.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.anesabml.zalando.R
import com.anesabml.zalando.databinding.ActivityMainBinding
import com.anesabml.zalando.extensions.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController

        val appbarConfiguration = AppBarConfiguration(setOf(R.id.productsFragment))

        setupActionBarWithNavController(navController, appbarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp() || findNavController(R.id.nav_host_fragment).navigateUp()
    }
}
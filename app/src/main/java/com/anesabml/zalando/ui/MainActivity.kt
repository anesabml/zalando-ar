package com.anesabml.zalando.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anesabml.zalando.databinding.ActivityMainBinding
import com.anesabml.zalando.extensions.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
    }
}
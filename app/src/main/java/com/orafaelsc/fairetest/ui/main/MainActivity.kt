package com.orafaelsc.fairetest.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.orafaelsc.fairetest.R
import com.orafaelsc.fairetest.commom.extensions.isConnected
import com.orafaelsc.fairetest.databinding.ActivityMainBinding
import com.orafaelsc.fairetest.di.MainModule
import com.orafaelsc.fairetest.ui.forecast.ForecastWeatherFragment
import com.orafaelsc.fairetest.ui.forecast.ForecastViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class MainActivity : AppCompatActivity() {

    private val viewModel: ForecastViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            navigateToForecastFragment()
        }
    }

    private fun navigateToForecastFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ForecastWeatherFragment.newInstance())
            .commitNow()
    }

}

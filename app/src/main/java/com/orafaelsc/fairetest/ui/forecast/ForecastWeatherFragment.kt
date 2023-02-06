package com.orafaelsc.fairetest.ui.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.orafaelsc.fairetest.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForecastWeatherFragment : Fragment() {

    private val viewModel: ForecastViewModel by viewModel()
    private var binding: FragmentMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMainBinding
        .inflate(inflater, container, false)
        .apply {
            setupView()
        }.also {
            binding = it
            viewModel.getForecast()
        }.root

    @Override
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setupView() {
//        TODO("Not yet implemented")
    }

    companion object {
        fun newInstance() = ForecastWeatherFragment()
    }
}

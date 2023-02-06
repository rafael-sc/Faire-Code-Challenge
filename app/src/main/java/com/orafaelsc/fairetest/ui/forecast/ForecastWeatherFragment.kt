package com.orafaelsc.fairetest.ui.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.orafaelsc.fairetest.R
import com.orafaelsc.fairetest.commom.extensions.isConnected
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
            if (context?.isConnected() == true) {
                viewModel.getForecast()
            } else {
                showDisconnectedWarning()
            }
        }.root

    @Override
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setupView() {
//        TODO("Not yet implemented")
    }

    private fun showDisconnectedWarning() {
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            R.string.no_internet_connection_available,
            Snackbar.LENGTH_INDEFINITE
        ).setAction(R.string.general_retry) {
            if (context?.isConnected() == true) {
                viewModel.getForecast()
            }
        }.show()
    }

    companion object {
        fun newInstance() = ForecastWeatherFragment()
    }
}

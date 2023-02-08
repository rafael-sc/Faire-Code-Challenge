package com.orafaelsc.fairetest.ui.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.orafaelsc.fairetest.R
import com.orafaelsc.fairetest.commom.exceptions.ApiException
import com.orafaelsc.fairetest.commom.extensions.isConnected
import com.orafaelsc.fairetest.commom.extensions.setupObserverOnCreated
import com.orafaelsc.fairetest.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForecastWeatherFragment : Fragment() {

    private val viewModel: ForecastViewModel by viewModel()
    private var binding: FragmentMainBinding? = null
    private val forecastAdapter: ForecastAdapter by lazy {
        ForecastAdapter {
            context?.run {
                Toast.makeText(this, "item $it clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMainBinding
        .inflate(inflater, container, false)
        .apply {
            binding = this
        }.also {
            setupView()
            initDataObserver()
            if (context?.isConnected() == true) {
                viewModel.getForecastData()
            } else {
                showDisconnectedWarning()
            }
        }.root

    @Override
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun initDataObserver() {
        viewModel.run {
            setupObserverOnCreated(forecastViewObject() to ::forecastViewObjectObserver)
            setupObserverOnCreated(loadingState() to ::loadingStateObserver)
            setupObserverOnCreated(errorState() to ::errorStateObserver)
        }
    }

    private fun errorStateObserver(exception: Throwable) {
        val message = if (exception is ApiException) {
            when (exception) {
                is ApiException.UnableToGetConsolidateWeatherException -> resources.getString(R.string.error_unable_to_load_consolidate_weather)
                is ApiException.UnableToGetWeatherForecastException -> resources.getString(R.string.error_unable_to_load_data)
            }
        } else {
            resources.getString(R.string.error_generic_message)
        }

        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_INDEFINITE
        ).setAction(R.string.general_retry) {
            viewModel.getForecastData()
        }.show()
    }

    private fun loadingStateObserver(isLoading: Boolean) {
        binding?.run {
            recyclerViewList.isVisible = isLoading.not()
            lottieViewLoading.isVisible = isLoading
        }
    }

    private fun forecastViewObjectObserver(viewObject: ForecastViewObject) {
        binding?.run {
            textViewCity.text = viewObject.cityName
            forecastAdapter.setItems(viewObject.weatherList)
            recyclerViewList.run {
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                adapter = forecastAdapter
            }
        }
    }

    private fun setupView() {
        binding?.run {
        }
    }

    private fun showDisconnectedWarning() {
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            R.string.no_internet_connection_available,
            Snackbar.LENGTH_INDEFINITE
        ).setAction(R.string.general_retry) {
            if (context?.isConnected() == true) {
                viewModel.getForecastData()
            } else {
                showDisconnectedWarning()
            }
        }.show()
    }

    companion object {
        fun newInstance() = ForecastWeatherFragment()
    }
}

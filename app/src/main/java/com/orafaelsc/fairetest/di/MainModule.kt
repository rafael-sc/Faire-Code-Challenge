package com.orafaelsc.fairetest.di

import com.orafaelsc.fairetest.commom.CoroutineDispatcherProvider
import com.orafaelsc.fairetest.domain.usecase.WeatherUseCase
import com.orafaelsc.fairetest.domain.usecase.WeatherUseCaseImpl
import com.orafaelsc.fairetest.ui.forecast.ForecastViewModel
import com.orafaelsc.fairetest.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

// object to hold MainScreen dependencies
object MainModule {
    private val instance: Module = module {

        viewModel {
            MainViewModel(
                dispatcherProvider = get()
            )
        }

        viewModel {
            ForecastViewModel(
                weatherUseCase = get(),
                dispatcherProvider = get()
            )
        }

        factory<WeatherUseCase> {
            WeatherUseCaseImpl(
                weatherRepository = get()
            )
        }

        factory<CoroutineDispatcherProvider> {
            CoroutineDispatcherProvider()
        }
    }

    val modules = instance +
        RetrofitModule.instance +
        ForecastModule.instance
}

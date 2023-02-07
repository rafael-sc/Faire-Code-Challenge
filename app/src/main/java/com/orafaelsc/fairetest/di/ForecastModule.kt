package com.orafaelsc.fairetest.di

import com.orafaelsc.fairetest.data.WeatherRepositoryImpl
import com.orafaelsc.fairetest.data.network.WeatherApi
import com.orafaelsc.fairetest.domain.repository.WeatherRepository
import com.orafaelsc.fairetest.domain.usecase.WeatherUseCase
import com.orafaelsc.fairetest.domain.usecase.WeatherUseCaseImpl
import com.orafaelsc.fairetest.ui.forecast.ForecastViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

// object to hold MainScreen dependencies
object ForecastModule {
    val instance = module {
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

        factory<WeatherRepository> {
            WeatherRepositoryImpl(
                weatherApi = get(),
                resourceProvider = get()
            )
        }

        factory<WeatherApi> {
            provideWeatherApi(
                get(qualifier = RetrofitQualifier)
            )
        }
    }

    private fun provideWeatherApi(retrofit: Retrofit) = retrofit.create(WeatherApi::class.java)
}

package com.orafaelsc.fairetest.di

import com.orafaelsc.fairetest.data.WeatherRepositoryImpl
import com.orafaelsc.fairetest.data.network.WeatherApi
import com.orafaelsc.fairetest.domain.repository.WeatherRepository
import org.koin.dsl.module
import retrofit2.Retrofit

// object to hold MainScreen dependencies
object ForecastModule {
    val instance = module {
        factory<WeatherRepository> {
            WeatherRepositoryImpl(
                weatherApi = get()
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

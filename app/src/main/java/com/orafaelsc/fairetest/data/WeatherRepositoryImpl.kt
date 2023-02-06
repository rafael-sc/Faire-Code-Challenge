package com.orafaelsc.fairetest.data

import com.orafaelsc.fairetest.data.network.WeatherApi
import com.orafaelsc.fairetest.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherApi: WeatherApi
//    private val weatherDataSource: WeatherDataSource
) : WeatherRepository {

    override suspend fun getWeatherForecast(locationId: String): Any {
        return weatherApi.getWeatherForecast(locationId)
    }
}

package com.orafaelsc.fairetest.domain.repository

interface WeatherRepository {
    suspend fun getWeatherForecast(locationId: String): Any
}

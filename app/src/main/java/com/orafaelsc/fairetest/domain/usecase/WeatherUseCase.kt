package com.orafaelsc.fairetest.domain.usecase

interface WeatherUseCase {
    suspend fun getWeatherForecast(locationId: String): Any
}

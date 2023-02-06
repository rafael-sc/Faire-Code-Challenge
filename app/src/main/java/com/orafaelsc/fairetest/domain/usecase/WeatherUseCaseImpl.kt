package com.orafaelsc.fairetest.domain.usecase

import com.orafaelsc.fairetest.domain.repository.WeatherRepository

class WeatherUseCaseImpl(
    private val weatherRepository: WeatherRepository
) : WeatherUseCase {
    override suspend fun getWeatherForecast(locationId: String): Any {
        return weatherRepository.getWeatherForecast(locationId)
    }
}

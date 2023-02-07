package com.orafaelsc.fairetest.domain.usecase

import com.orafaelsc.fairetest.domain.repository.WeatherRepository
import com.orafaelsc.fairetest.ui.forecast.ForecastViewObject

class WeatherUseCaseImpl(
    private val weatherRepository: WeatherRepository
) : WeatherUseCase {
    override suspend fun getWeatherForecast(locationId: String): ForecastViewObject {
        return weatherRepository.getWeatherForecast(locationId)
    }
}

package com.orafaelsc.fairetest.domain.usecase

import com.orafaelsc.fairetest.ui.forecast.ForecastViewObject

interface WeatherUseCase {
    suspend fun getWeatherForecast(locationId: String): ForecastViewObject
}

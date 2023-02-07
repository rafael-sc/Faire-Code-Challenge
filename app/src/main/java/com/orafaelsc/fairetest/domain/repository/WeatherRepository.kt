package com.orafaelsc.fairetest.domain.repository

import com.orafaelsc.fairetest.ui.forecast.ForecastViewObject

interface WeatherRepository {
    suspend fun getWeatherForecast(locationId: String): ForecastViewObject
}

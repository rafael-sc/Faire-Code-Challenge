package com.orafaelsc.fairetest.ui.forecast

data class ForecastViewObject(
    val cityName: String,
    val weatherList: List<ConsolidatedWeather>
)

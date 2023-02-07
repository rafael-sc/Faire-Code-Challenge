package com.orafaelsc.fairetest.ui.forecast

data class ForecastViewObject(
    val cityName: String,
    val imageUrl: String,
    val actualTemp: Double,
    val maxTemp: Double,
    val minTemp: Double,
    val weatherStateName: String
)

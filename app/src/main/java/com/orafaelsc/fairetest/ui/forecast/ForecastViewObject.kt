package com.orafaelsc.fairetest.ui.forecast

data class ForecastViewObject(
    val cityName: String,
    val imageUrl: String,
    val actualTemp: String,
    val higherTemp: String,
    val lowerTemp: String,
    val weatherStateName: String
)

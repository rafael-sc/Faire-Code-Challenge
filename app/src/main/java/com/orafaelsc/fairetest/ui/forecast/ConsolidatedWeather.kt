package com.orafaelsc.fairetest.ui.forecast

data class ConsolidatedWeather(
    val actualTemp: String,
    val higherTemp: String,
    val lowerTemp: String,
    val imageUrl: String,
    val stateName: String
)

package com.orafaelsc.fairetest.domain.extensions

import com.orafaelsc.fairetest.data.network.model.WeatherResponse
import com.orafaelsc.fairetest.ui.forecast.ForecastViewObject

fun WeatherResponse.toDomain() = ForecastViewObject(
    cityName = title,
    imageUrl = "https://cdn.faire.com/static/mobile-take-home/icons/${consolidated_weather[0].weather_state_abbr}.png",
    actualTemp = consolidated_weather[0].the_temp,
    maxTemp = consolidated_weather[0].max_temp,
    minTemp = consolidated_weather[0].min_temp,
    weatherStateName = consolidated_weather[0].weather_state_name
)

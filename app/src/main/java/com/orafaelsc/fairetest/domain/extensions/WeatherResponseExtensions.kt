package com.orafaelsc.fairetest.domain.extensions

import com.orafaelsc.fairetest.commom.ResourceProvider
import com.orafaelsc.fairetest.data.network.model.WeatherResponse
import com.orafaelsc.fairetest.ui.forecast.ForecastViewObject
import kotlin.math.roundToInt

fun WeatherResponse.toDomain(resourceProvider: ResourceProvider) = ForecastViewObject(
    cityName = title,
    imageUrl = "https://cdn.faire.com/static/mobile-take-home/icons/${consolidated_weather[0].weather_state_abbr}.png",
    actualTemp = consolidated_weather[0].the_temp.roundToInt().toString(),
    higherTemp = consolidated_weather[0].max_temp.roundToInt().toString(),
    lowerTemp = consolidated_weather[0].min_temp.roundToInt().toString(),
    weatherStateName = consolidated_weather[0].weather_state_name
)

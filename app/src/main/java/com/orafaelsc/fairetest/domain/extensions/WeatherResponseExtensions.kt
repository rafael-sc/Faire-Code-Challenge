package com.orafaelsc.fairetest.domain.extensions

import com.orafaelsc.fairetest.R
import com.orafaelsc.fairetest.commom.ResourceProvider
import com.orafaelsc.fairetest.data.network.model.WeatherResponse
import com.orafaelsc.fairetest.ui.forecast.ForecastViewObject
import kotlin.math.roundToInt

fun WeatherResponse.toDomain(resourceProvider: ResourceProvider) = ForecastViewObject(
    cityName = title,
    imageUrl = resourceProvider.getString(
        R.string.base_image_url,
        consolidated_weather[0].weather_state_abbr
    ),
    actualTemp = resourceProvider.getString(R.string.actual_temp, consolidated_weather[0].the_temp.roundToInt()),
    higherTemp = resourceProvider.getString(R.string.max_temp, consolidated_weather[0].max_temp.roundToInt()),
    lowerTemp = resourceProvider.getString(R.string.min_temp, consolidated_weather[0].min_temp.roundToInt()),
    weatherStateName = consolidated_weather[0].weather_state_name
)



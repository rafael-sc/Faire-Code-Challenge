package com.orafaelsc.fairetest.domain.extensions

import com.orafaelsc.fairetest.R
import com.orafaelsc.fairetest.commom.ResourceProvider
import com.orafaelsc.fairetest.data.network.model.WeatherResponse
import com.orafaelsc.fairetest.ui.forecast.ConsolidatedWeather
import com.orafaelsc.fairetest.ui.forecast.ForecastViewObject
import kotlin.math.roundToInt

fun WeatherResponse.toDomain(resourceProvider: ResourceProvider) = ForecastViewObject(
    cityName = title,
    weatherList = consolidated_weather.map {
        ConsolidatedWeather(
            actualTemp = resourceProvider.getString(
                R.string.actual_temp,
                it.the_temp.roundToInt()
            ),
            higherTemp = resourceProvider.getString(
                R.string.max_temp,
                it.max_temp.roundToInt()
            ),
            lowerTemp = resourceProvider.getString(
                R.string.min_temp,
                it.min_temp.roundToInt()
            ),
            imageUrl = resourceProvider.getString(
                R.string.base_image_url,
                it.weather_state_abbr
            ),
            stateName = it.weather_state_name
        )
    }
)

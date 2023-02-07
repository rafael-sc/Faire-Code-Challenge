package com.orafaelsc.fairetest.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConsolidatedWeatherResponse(
    @field:Json(name = "air_pressure") val air_pressure: Double,
    @field:Json(name = "applicable_date") val applicable_date: String,
    @field:Json(name = "created") val created: String,
    @field:Json(name = "humidity") val humidity: Int,
    @field:Json(name = "id") val id: Long,
    @field:Json(name = "max_temp") val max_temp: Double,
    @field:Json(name = "min_temp") val min_temp: Double,
    @field:Json(name = "predictability") val predictability: Int,
    @field:Json(name = "the_temp") val the_temp: Double,
    @field:Json(name = "visibility") val visibility: Double,
    @field:Json(name = "weather_state_abbr") val weather_state_abbr: String,
    @field:Json(name = "weather_state_name") val weather_state_name: String,
    @field:Json(name = "wind_direction") val wind_direction: Double,
    @field:Json(name = "wind_direction_compass") val wind_direction_compass: String,
    @field:Json(name = "wind_speed") val wind_speed: Double
)

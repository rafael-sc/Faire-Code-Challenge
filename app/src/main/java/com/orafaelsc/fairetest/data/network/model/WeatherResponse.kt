package com.orafaelsc.fairetest.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponse(
    @field:Json(name = "consolidated_weather") val consolidated_weather: List<ConsolidatedWeatherResponse>,
    @field:Json(name = "latt_long") val latt_long: String,
    @field:Json(name = "location_type") val location_type: String,
    @field:Json(name = "parent") val parent: ParentResponse,
    @field:Json(name = "sources") val sources: List<SourceResponse>,
    @field:Json(name = "sun_rise") val sun_rise: String,
    @field:Json(name = "sun_set") val sun_set: String,
    @field:Json(name = "time") val time: String,
    @field:Json(name = "timezone") val timezone: String,
    @field:Json(name = "timezone_name") val timezone_name: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "woeid") val woeid: Int
)

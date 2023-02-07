package com.orafaelsc.fairetest.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ParentResponse(
    @field:Json(name = "latt_long") val latt_long: String,
    @field:Json(name = "location_type") val location_type: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "woeid") val woeid: Int
)

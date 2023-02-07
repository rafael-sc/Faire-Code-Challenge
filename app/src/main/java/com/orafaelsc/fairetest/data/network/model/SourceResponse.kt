package com.orafaelsc.fairetest.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SourceResponse(
    @field:Json(name = "crawl_rate") val crawl_rate: Int,
    @field:Json(name = "slug") val slug: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "url") val url: String
)

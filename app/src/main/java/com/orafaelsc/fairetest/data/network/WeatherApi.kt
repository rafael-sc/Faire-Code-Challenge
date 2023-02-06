package com.orafaelsc.fairetest.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherApi {

    @GET("{location_id}.json")
    suspend fun getWeatherForecast(@Path("location_id") locationId: String): Response<Any>
}

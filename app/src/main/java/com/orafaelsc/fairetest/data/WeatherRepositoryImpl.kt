package com.orafaelsc.fairetest.data

import com.orafaelsc.fairetest.commom.ResourceProvider
import com.orafaelsc.fairetest.commom.exceptions.ApiException
import com.orafaelsc.fairetest.data.network.WeatherApi
import com.orafaelsc.fairetest.domain.extensions.toDomain
import com.orafaelsc.fairetest.domain.repository.WeatherRepository
import com.orafaelsc.fairetest.ui.forecast.ForecastViewObject

class WeatherRepositoryImpl(
    private val weatherApi: WeatherApi,
    private val resourceProvider: ResourceProvider,
) : WeatherRepository {

    override suspend fun getWeatherForecast(locationId: String): ForecastViewObject {
        val result = weatherApi.getWeatherForecast(locationId)
        if (result.isSuccessful) {
            return result.body()?.toDomain(resourceProvider)
                ?: throw ApiException.UnableToGetWeatherForecastException()
        } else {
            throw ApiException.UnableToGetWeatherForecastException()
        }
    }
}

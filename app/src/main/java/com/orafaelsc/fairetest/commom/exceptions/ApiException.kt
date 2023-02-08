package com.orafaelsc.fairetest.commom.exceptions

sealed class ApiException : Throwable() {
    class UnableToGetWeatherForecastException : ApiException()
    class UnableToGetConsolidateWeatherException : ApiException()
}

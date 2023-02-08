package com.orafaelsc.fairetest.ui.forecast

import androidx.lifecycle.viewModelScope
import com.orafaelsc.fairetest.commom.BaseViewModel
import com.orafaelsc.fairetest.commom.CoroutineDispatcherProvider
import com.orafaelsc.fairetest.commom.exceptions.ApiException
import com.orafaelsc.fairetest.domain.usecase.WeatherUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ForecastViewModel(
    private val weatherUseCase: WeatherUseCase,
    dispatcherProvider: CoroutineDispatcherProvider
) : BaseViewModel(dispatcherProvider) {

    private val forecastViewObject = MutableSharedFlow<ForecastViewObject>()
    fun forecastViewObject(): SharedFlow<ForecastViewObject> = forecastViewObject

    private val loadingState = MutableSharedFlow<Boolean>()
    fun loadingState(): SharedFlow<Boolean> = loadingState

    fun getForecastData() {
        viewModelScope.launch(mainExceptionHandler) {
            loadingState.emit(true)
            withContext(ioExceptionHandler) {
                val result = weatherUseCase.getWeatherForecast("4418")
                if (result.weatherList.isEmpty()) {
                    errorState.emit(ApiException.UnableToGetConsolidateWeatherException())
                } else {
                    forecastViewObject.emit(result)
                }
                loadingState.emit(false)
            }
        }
    }
}

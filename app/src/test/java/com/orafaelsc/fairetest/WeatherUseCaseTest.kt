package com.orafaelsc.fairetest

import com.orafaelsc.fairetest.commom.ResourceProvider
import com.orafaelsc.fairetest.commom.exceptions.ApiException
import com.orafaelsc.fairetest.data.WeatherRepositoryImpl
import com.orafaelsc.fairetest.data.network.WeatherApi
import com.orafaelsc.fairetest.data.network.model.ConsolidatedWeatherResponse
import com.orafaelsc.fairetest.data.network.model.ParentResponse
import com.orafaelsc.fairetest.data.network.model.SourceResponse
import com.orafaelsc.fairetest.data.network.model.WeatherResponse
import com.orafaelsc.fairetest.domain.repository.WeatherRepository
import com.orafaelsc.fairetest.domain.usecase.WeatherUseCase
import com.orafaelsc.fairetest.domain.usecase.WeatherUseCaseImpl
import io.kotlintest.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class WeatherUseCaseTest {

    private lateinit var weatherUseCase: WeatherUseCase
    private lateinit var weatherRepository: WeatherRepository

    @MockK
    private lateinit var weatherApi: WeatherApi

    @MockK
    private lateinit var resourceProvider: ResourceProvider

    @MockK
    private lateinit var errorResponse: ResponseBody

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        weatherRepository = WeatherRepositoryImpl(weatherApi, resourceProvider)
        weatherUseCase = WeatherUseCaseImpl(weatherRepository)
    }

    private val testConsolidatedWeatherResponse = listOf(
        ConsolidatedWeatherResponse(
            air_pressure = 0.0,
            applicable_date = "date 0",
            created = "created 0",
            humidity = 11,
            id = 0,
            max_temp = 100.0,
            min_temp = 0.0,
            predictability = 75,
            the_temp = 11.0,
            visibility = 0.0,
            weather_state_abbr = "abbr 0",
            weather_state_name = "state name 0",
            wind_direction = 0.0,
            wind_direction_compass = "wind direction compass 0",
            wind_speed = 0.0
        ),
        ConsolidatedWeatherResponse(
            air_pressure = 0.0,
            applicable_date = "date 1",
            created = "created 1",
            humidity = 11,
            id = 1,
            max_temp = 100.0,
            min_temp = 0.0,
            predictability = 75,
            the_temp = 11.0,
            visibility = 0.0,
            weather_state_abbr = "abbr 1",
            weather_state_name = "state name 1",
            wind_direction = 0.0,
            wind_direction_compass = "wind direction compass 1",
            wind_speed = 0.0
        )
    )
    private val testParentResponse = ParentResponse(
        latt_long = " parent lattlong",
        location_type = "parent location type",
        title = "parent title",
        woeid = 0
    )

    private val testSourceResponse = listOf<SourceResponse>(
        SourceResponse(
            crawl_rate = 0,
            slug = "slug",
            title = "title",
            url = "url"
        )
    )
    private val weatherResponse = WeatherResponse(
        consolidated_weather = testConsolidatedWeatherResponse,
        latt_long = "mocked latt long",
        location_type = "mocked location type",
        parent = testParentResponse,
        sources = testSourceResponse,
        sun_rise = "sun rise time",
        sun_set = "sun set time",
        time = "time",
        timezone = "timezone",
        timezone_name = "timezone name",
        title = "title",
        woeid = 0
    )

    @Test
    fun `getWeatherForecast() - should return ForecastViewObject - ForecastViewObject should have same properties as the response`() =
        runTest {
            // arrange
            coEvery { weatherApi.getWeatherForecast(any()) } returns Response.success(
                weatherResponse
            )
            every {
                resourceProvider.getString(
                    R.string.base_image_url,
                    any()
                )
            } returns "base image url"
            every { resourceProvider.getString(R.string.actual_temp, any()) } returns "actual temp"
            every { resourceProvider.getString(R.string.min_temp, any()) } returns "min temp"
            every { resourceProvider.getString(R.string.max_temp, any()) } returns "max temp"

            // act
            val viewObject = weatherUseCase.getWeatherForecast("")

            // assert
            viewObject.weatherList.size shouldBe 2
            viewObject.cityName shouldBe weatherResponse.title
        }

    @Test(expected = ApiException.UnableToGetConsolidateWeatherException::class)
    fun `getWeatherForecast() - api result is error - should throw UnableToGetConsolidateWeatherException`() =
        runTest {
            // arrange
            coEvery { weatherApi.getWeatherForecast(any()) } throws ApiException.UnableToGetConsolidateWeatherException()

            // act
            weatherUseCase.getWeatherForecast("")

            // assert
            // test should throw UnableToGetWeatherForecastException
        }

    @Test(expected = ApiException.UnableToGetWeatherForecastException::class)
    fun `getWeatherForecast() - api result is error - should throw UnableToGetWeatherForecastException`() =
        runTest {
            // arrange
            val mockedErrorResponse = Response.error<WeatherResponse>(
                400,
                "{\"key\":[\"somestuff\"]}"
                    .toResponseBody("application/json".toMediaTypeOrNull())
            )
            coEvery { weatherApi.getWeatherForecast(any()) } returns mockedErrorResponse

            // act
            weatherUseCase.getWeatherForecast("")

            // assert
            // test should throw UnableToGetWeatherForecastException
        }
}

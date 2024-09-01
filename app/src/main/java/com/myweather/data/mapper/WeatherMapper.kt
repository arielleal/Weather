package com.myweather.data.mapper

import android.content.res.Resources
import android.content.res.loader.ResourcesProvider
import com.myweather.core.ResourceProvider
import com.myweather.data.model.ForecastResponse
import com.myweather.data.model.ResultResponse
import com.myweather.data.model.WeatherResponse
import com.myweather.domain.model.Forecast
import com.myweather.domain.model.Results
import com.myweather.domain.model.Weather

class WeatherMapper(
    val resource: ResourceProvider
) {
    fun map(response: WeatherResponse) = Weather(
        type = response.type,
        weatherResults = weatherResultsMap(response.weatherResults)
    )

    private fun weatherResultsMap(result: ResultResponse) = Results(
        temp = result.temp,
        time = result.time,
        city = result.city,
        description = result.description,
        humidity = result.humidity.toString(),
        windSpeedy = result.windSpeedy,
        conditionSlug = resource.getIdentifier(result.conditionSlug),
        forecastList = result.forecastList.map {
            forecastListResponse -> forecastListResponse.toForecast()
        }
    )

    private fun ForecastResponse.toForecast() = Forecast(
        date = date,
        weekday = weekday,
        maxTemp = maxTemp,
        minTemp = minTemp,
        description = description,
        condition = resource.getIdentifier(condition)
    )
}
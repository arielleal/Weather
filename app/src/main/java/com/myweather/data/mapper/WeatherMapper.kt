package com.myweather.data.mapper

import com.myweather.data.model.ForecastResponse
import com.myweather.data.model.ResultResponse
import com.myweather.data.model.WeatherResponse
import com.myweather.domain.model.Forecast
import com.myweather.domain.model.Results
import com.myweather.domain.model.Weather

class WeatherMapper {
    fun map(response: WeatherResponse) = Weather(
        type = response.type,
        weatherResults = weatherResultsMap(response.weatherResults)
    )

    private fun weatherResultsMap(result: ResultResponse) = Results(
        temp = result.temp.toString(),
        city = result.city,
        humidity = result.humidity.toString(),
        windSpeedy = result.windSpeedy,
        moonPhase = result.moonPhase,
        forecastList = result.forecastList.map {
            forecastListResponse -> forecastListResponse.toForecast()
        }
    )

    private fun ForecastResponse.toForecast() = Forecast(
        date = date,
        weekday = weekday,
        maxTemp = maxTemp.toString(),
        minTemp = minTemp.toString(),
        description = description,
        condition = condition
    )
}
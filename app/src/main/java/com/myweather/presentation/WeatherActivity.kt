package com.myweather.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.myweather.R
import com.myweather.core.State
import com.myweather.core.toTemperature
import com.myweather.databinding.WeatherMainActivityBinding
import com.myweather.domain.model.Forecast
import com.myweather.domain.model.Weather
import com.myweather.presentation.adapter.WeatherMainAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherActivity : AppCompatActivity(R.layout.weather_main_activity) {

    private val binding by lazy { WeatherMainActivityBinding.inflate(layoutInflater) }
    private val viewModel: WeatherViewModel by viewModel()
    private val weatherMainAdapter by lazy { WeatherMainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        stateListener()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getWeather()
    }

    private fun stateListener() {
        viewModel.state.observe(this, Observer {
            state ->
            when(state) {
                is State.Error -> {}
                is State.Success -> { handleContent(state.result) }
                is State.Loading -> {}
            }
        })
    }

    private fun handleContent(weather: Weather) = with(binding) {
        weatherImage.setImageResource(weather.weatherResults.conditionSlug)
        city.text = weather.weatherResults.city
        temp.text = weather.weatherResults.temp.toTemperature()
        description.text = weather.weatherResults.description
        time.text = weather.weatherResults.time
        setupAdapter(weather.weatherResults.forecastList)
    }

    private fun setupAdapter(forecast: List<Forecast>) = with(binding) {
        rvList.adapter = weatherMainAdapter
        rvList.layoutManager = LinearLayoutManager(this@WeatherActivity)
        weatherMainAdapter.submitList(forecast)
    }
}
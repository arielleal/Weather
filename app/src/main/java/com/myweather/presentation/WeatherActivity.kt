package com.myweather.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.myweather.R
import com.myweather.core.State
import com.myweather.databinding.WeatherMainActivityBinding
import com.myweather.domain.model.Weather
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherActivity : AppCompatActivity(R.layout.weather_main_activity) {

    private val binding by lazy { WeatherMainActivityBinding.inflate(layoutInflater) }
    private val viewModel: WeatherViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stateListener()
        viewModel.handleGetWeather()
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

    private fun handleContent(weather: Weather) {

    }
}
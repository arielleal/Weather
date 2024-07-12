package com.myweather.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.myweather.core.State
import com.myweather.domain.model.Weather

class WeatherActivity : AppCompatActivity() {

    private val viewModel: WeatherViewModel by viewModel()
    //private val binding: WeatherActivityBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stateListener()
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
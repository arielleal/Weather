package com.myweather.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.myweather.R
import com.myweather.core.getWeatherLocation
import com.myweather.core.hideKeyboard
import com.myweather.core.toTemperature
import com.myweather.databinding.WeatherMainActivityBinding
import com.myweather.domain.model.Forecast
import com.myweather.domain.model.Weather
import com.myweather.presentation.adapter.WeatherMainAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherActivity : AppCompatActivity(R.layout.weather_main_activity) {

    private lateinit var fusedLocationProvider: FusedLocationProviderClient
    private val binding by lazy { WeatherMainActivityBinding.inflate(layoutInflater) }
    private val viewModel: WeatherViewModel by viewModel()
    private val weatherMainAdapter by lazy { WeatherMainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        stateObserver()
        setupListener()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getStorage()
    }

    private fun stateObserver() {
        viewModel.state.observe(this, Observer
            { state ->
                showLoading(loadingIsVisible = state.loading)
                state.result?.let { handleContent(weather = it) }
            }
        )
    }

    private fun showLoading(loadingIsVisible: Boolean) = with(binding){
        loading.isVisible = loadingIsVisible
        loadingGroup.isVisible = loadingIsVisible.not()
    }

    private fun handleContent(weather: Weather) = with(binding) {
        hideKeyboard()

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

    private fun setupListener() = with(binding) {
        btLocation.setOnClickListener { fetchLocation() }
        btSearch.setOnClickListener { viewModel.getWeather() }
        etSearch.apply {
            doAfterTextChanged { text ->
                viewModel.updateCityName(text.toString())
            }
        }
    }
    private fun fetchLocation() {
        fusedLocationProvider = LocationServices.getFusedLocationProviderClient(this)

        getWeatherLocation(
            fusedLocationProvider = fusedLocationProvider,
            location = { lon, lat ->
                viewModel.setLocation(
                    longitude = lon,
                    latitude = lat
                )
            }
        )
    }
}
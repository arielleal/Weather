package com.myweather.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.myweather.domain.usecase.StorageSearchUseCase
import com.myweather.domain.usecase.WeatherUseCase
import com.myweather.util.MainDispatcherRule
import com.myweather.util.weatherResponseStub
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifyOrder
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class WeatherViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainDispatcherRule = MainDispatcherRule()

    private var stateObserver: Observer<WeatherState> = mockk(relaxed = true)

    private lateinit var viewModel: WeatherViewModel
    private val weatherUseCase: WeatherUseCase = mockk()
    private val storageUseCase: StorageSearchUseCase = mockk(relaxed = true)

    @Before
    fun setup() {
        viewModel = WeatherViewModel(
            weatherUseCase = weatherUseCase,
            storageUseCase =storageUseCase
        )
        viewModel.state.observeForever(stateObserver)
    }

    @Test
    fun `GetWeather should return success state when useCase return correctly response`() = runBlocking {
        // Given
        val response = weatherResponseStub()
        val cityName = "Hotolandia"

        val initialState = WeatherState()
        val loadingState = initialState.showLoading()
        val successState = loadingState.setSuccess(result = response)

        every { weatherUseCase(any()) } returns flowOf(response)
        viewModel.updateCityName(cityName)

        //When
        viewModel.getWeather()

        //Then
        verifyOrder {
            stateObserver.onChanged(initialState)
            stateObserver.onChanged(loadingState)
            stateObserver.onChanged(successState)
        }
    }

    @Test
    fun `GetWeather should return error state when useCase return exception`() = runBlocking {
        // Given
        val error = Throwable()
        val cityName = "Hotolandia"

        val initialState = WeatherState()
        val loadingState = initialState.showLoading()
        val errorState = loadingState.setError()

        every { weatherUseCase(any()) } returns flow { throw error }
        viewModel.updateCityName(cityName)

        //When
        viewModel.getWeather()

        //Then
        verifyOrder {
            stateObserver.onChanged(initialState)
            stateObserver.onChanged(loadingState)
            stateObserver.onChanged(errorState)
        }
    }
}
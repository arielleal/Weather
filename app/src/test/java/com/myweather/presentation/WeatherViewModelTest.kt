package com.myweather.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.myweather.core.State
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

    private var stateObserver: Observer<State> = mockk(relaxed = true)

    private lateinit var viewModel: WeatherViewModel
    private val useCase: WeatherUseCase = mockk()

    @Before
    fun setup() {
        viewModel = WeatherViewModel(
            useCase = useCase
        )
        viewModel.state.observeForever(stateObserver)
    }

    @Test
    fun `GetWeather should return success state when useCase return correctly response`() = runBlocking {
        // Given
        val response = weatherResponseStub()
        every { useCase(any()) } returns flowOf(response)

        //When
        viewModel.getWeather()

        //Then
        verifyOrder {
            stateObserver.onChanged(State.Loading(true))
            stateObserver.onChanged(State.Loading(false))
            stateObserver.onChanged(State.Success(response))
        }
    }

    @Test
    fun `GetWeather should return error state when useCase return exception`() = runBlocking {
        // Given
        val error = Throwable()
        every { useCase(any()) } returns flow { throw error}

        //When
        viewModel.getWeather()

        //Then
        verifyOrder {
            stateObserver.onChanged(State.Loading(true))
            stateObserver.onChanged(State.Loading(false))
            stateObserver.onChanged(State.Error(error))
        }
    }
}
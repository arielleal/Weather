package com.myweather.presentation.di

import com.myweather.presentation.WeatherViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {

    private fun viewModelModule() : Module {
        return module {
            factory {
                WeatherViewModel(
                    weatherUseCase = get(),
                    storageUseCase = get()
                )
            }
        }
    }

    fun loadModule() {
        loadKoinModules(viewModelModule())
    }
}
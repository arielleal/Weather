package com.myweather.domain.di

import com.myweather.domain.usecase.WeatherUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {

    private fun domainModule() : Module {
        return module {
            factory { WeatherUseCase(repository = get()) }
        }
    }

    fun loadModule() {
        loadKoinModules(domainModule())
    }
}
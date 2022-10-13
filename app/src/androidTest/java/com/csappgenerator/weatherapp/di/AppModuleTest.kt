package com.csappgenerator.weatherapp.di

import com.csappgenerator.weatherapp.data.repository.FakeWeatherRepository
import com.csappgenerator.weatherapp.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {
    @Provides
    @Singleton
    fun provideRepository(): WeatherRepository {
        return FakeWeatherRepository()
    }
}
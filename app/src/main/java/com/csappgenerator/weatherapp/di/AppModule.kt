package com.csappgenerator.weatherapp.di

import com.csappgenerator.weatherapp.common.Constants
import com.csappgenerator.weatherapp.data.remote.WeatherApi
import com.csappgenerator.weatherapp.data.repository.WeatherRepositoryImpl
import com.csappgenerator.weatherapp.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {
    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: WeatherApi): WeatherRepository {
        return WeatherRepositoryImpl(api)
    }
}
package com.csappgenerator.weatherapp.domain.repository

import com.csappgenerator.weatherapp.data.local.entity.WeatherEntity
import com.csappgenerator.weatherapp.domain.model.Weather

interface WeatherRepository {
    suspend fun getWeatherData(): List<Weather>
    suspend fun getWeatherDataFromDb(): List<WeatherEntity>
    suspend fun insertAll(weatherList: List<WeatherEntity>)
    suspend fun deleteAll()
}
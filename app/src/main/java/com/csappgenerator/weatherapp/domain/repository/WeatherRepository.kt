package com.csappgenerator.weatherapp.domain.repository

import com.csappgenerator.weatherapp.domain.model.Weather

interface WeatherRepository {
    suspend fun getWeatherData(): List<Weather>
}
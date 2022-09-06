package com.csappgenerator.weatherapp.domain.repository

import com.csappgenerator.weatherapp.data.remote.dto.WeatherDto

interface WeatherRepository {
    suspend fun getWeatherData(cityName: String): WeatherDto
}
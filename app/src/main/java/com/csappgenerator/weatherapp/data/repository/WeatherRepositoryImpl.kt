package com.csappgenerator.weatherapp.data.repository

import com.csappgenerator.weatherapp.data.remote.WeatherApi
import com.csappgenerator.weatherapp.data.remote.dto.WeatherDto
import com.csappgenerator.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val api: WeatherApi) : WeatherRepository {
    override suspend fun getWeatherData(cityName: String): WeatherDto {
        return api.getWeatherData(cityName)
    }
}
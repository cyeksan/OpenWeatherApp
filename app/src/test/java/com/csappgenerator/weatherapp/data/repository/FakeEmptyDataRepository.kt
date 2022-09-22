package com.csappgenerator.weatherapp.data.repository

import com.csappgenerator.weatherapp.domain.model.Weather
import com.csappgenerator.weatherapp.domain.repository.WeatherRepository


class FakeEmptyDataRepository : WeatherRepository {
    override suspend fun getWeatherData(): List<Weather> {
        return emptyList()
    }
}
package com.csappgenerator.weatherapp.presentation

import com.csappgenerator.weatherapp.domain.model.Weather

data class WeatherState(
    val isLoading: Boolean = false,
    val weatherList: List<Weather> = emptyList(),
    val error: String = ""
)

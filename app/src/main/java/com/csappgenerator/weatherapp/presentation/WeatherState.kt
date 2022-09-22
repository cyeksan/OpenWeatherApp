package com.csappgenerator.weatherapp.presentation

import com.csappgenerator.weatherapp.domain.model.Weather

sealed class WeatherState {
    object Loading : WeatherState()
    class Success(val weatherList: List<Weather>) : WeatherState()
    class Error(val message: String) : WeatherState()
}

package com.csappgenerator.weatherapp.domain.model


data class Weather(
    val id: Int?,
    val main: Main?,
    val name: String?,
    val visibility: Int?,
    val clouds: Clouds?,
    val wind: Wind?,
    val weather: List<WeatherDetail>?,
)
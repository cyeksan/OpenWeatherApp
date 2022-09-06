package com.csappgenerator.weatherapp.domain.model

data class WeatherDetail(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)
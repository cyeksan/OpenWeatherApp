package com.csappgenerator.weatherapp.data.remote.dto

import com.csappgenerator.weatherapp.domain.model.WeatherDetail

data class WeatherDetailDto(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)

fun WeatherDetailDto.toWeatherDetail(): WeatherDetail {
    return WeatherDetail(
        description = description,
        icon = icon,
        id = id,
        main = main
    )
}


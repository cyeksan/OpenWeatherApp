package com.csappgenerator.weatherapp.data.remote.dto

import com.csappgenerator.weatherapp.domain.model.Weather

data class WeatherDto(
    val id: Int,
    val main: MainDto,
    val name: String,
    val visibility: Int,
    val clouds: CloudsDto,
    val wind: WindDto,
    val weather: List<WeatherDetailDto>,
)

fun WeatherDto.toWeather(): Weather {
    return Weather(
        id = id,
        name = name,
        description = weather[0].description,
        icon = weather[0].description,
        humidity = main.humidity,
        temp = main.temp,
        all = clouds.all,
        visibility = visibility,
        speed = wind.speed,
        deg = wind.deg,
        pressure = main.pressure
    )
}
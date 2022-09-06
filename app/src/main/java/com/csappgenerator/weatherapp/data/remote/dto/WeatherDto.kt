package com.csappgenerator.weatherapp.data.remote.dto

import com.csappgenerator.weatherapp.domain.model.Weather

data class WeatherDto(
    val id: Int?,
    val main: MainDto?,
    val name: String?,
    val visibility: Int?,
    val clouds: CloudsDto?,
    val wind: WindDto?,
    val weather: List<WeatherDetailDto>,
)

fun WeatherDto.toWeather(): Weather {
    return Weather(
        id = id,
        main = main?.toMain(),
        name = name,
        visibility = visibility,
        clouds = clouds?.toClouds(),
        wind = wind?.toWind(),
        weather = weather.map {
            it.toWeatherDetail()
        },
    )
}
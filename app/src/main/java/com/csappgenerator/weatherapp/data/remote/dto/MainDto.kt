package com.csappgenerator.weatherapp.data.remote.dto

import com.csappgenerator.weatherapp.domain.model.Main

data class MainDto(
    val feels_like: Double?,
    val grnd_level: Int?,
    val humidity: Int?,
    val pressure: Int?,
    val sea_level: Int?,
    val temp: Double?,
    val temp_max: Double?,
    val temp_min: Double?
)

fun MainDto.toMain(): Main {
    return Main(
        feels_like = feels_like,
        humidity = humidity,
        pressure = pressure,
        temp = temp,
        temp_min = temp_min,
        temp_max = temp_max
    )
}
package com.csappgenerator.weatherapp.domain.model

import com.csappgenerator.weatherapp.data.local.entity.WeatherEntity


data class Weather(
    val id: Int? = null,
    val name: String?,
    val description: String?,
    val humidity: Int?,
    val icon: String,
    val temp: Double?,
    val all: Int?,
    val visibility: Int?,
    val speed: Double?,
    val deg: Int?,
    val pressure: Int?
) {
    fun toWeatherEntity(): WeatherEntity {
        return WeatherEntity(
            pressure = pressure,
            description = description,
            name = name,
            humidity = humidity,
            temp = temp,
            all = all,
            visibility = visibility,
            speed = speed,
            deg = deg,
            icon = icon
        )
    }
}




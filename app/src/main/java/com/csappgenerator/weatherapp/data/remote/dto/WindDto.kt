package com.csappgenerator.weatherapp.data.remote.dto

import com.csappgenerator.weatherapp.domain.model.Wind

data class WindDto(
    val speed: Double?,
    val deg: Int?,
)

fun WindDto.toWind(): Wind {
    return Wind(
        speed = speed,
        deg = deg
    )
}
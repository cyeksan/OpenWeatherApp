package com.csappgenerator.weatherapp.data.remote.dto

import com.csappgenerator.weatherapp.domain.model.Clouds

data class CloudsDto(
    val all: Int?
)

fun CloudsDto.toClouds(): Clouds {
    return Clouds(
        all = all
    )
}
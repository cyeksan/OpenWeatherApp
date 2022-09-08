package com.csappgenerator.weatherapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.csappgenerator.weatherapp.domain.model.Weather

@Entity
data class WeatherEntity(
    @PrimaryKey
    val id: Int? = null,
    val description: String?,
    val name: String?,
    val humidity: Int?,
    val icon: String,
    val temp: Double?,
    val all: Int?,
    val visibility: Int?,
    val speed: Double?,
    val deg: Int?,
    val pressure: Int?
) {
    fun toWeather(): Weather {
        return Weather(
            id,
            name,
            description,
            humidity,
            icon,
            temp,
            all,
            visibility,
            speed,
            deg,
            pressure
        )
    }
}




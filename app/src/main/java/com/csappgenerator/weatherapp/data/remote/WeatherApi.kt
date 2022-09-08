package com.csappgenerator.weatherapp.data.remote

import com.csappgenerator.weatherapp.common.Constants
import com.csappgenerator.weatherapp.data.remote.dto.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather?")
    suspend fun getWeatherData(
        @Query("q") cityName: String,
        @Query("appid") appId: String = Constants.API_KEY,
    ): WeatherDto

    companion object {
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    }
}
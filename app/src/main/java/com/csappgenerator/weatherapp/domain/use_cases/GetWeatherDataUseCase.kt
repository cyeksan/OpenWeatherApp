package com.csappgenerator.weatherapp.domain.use_cases


import com.csappgenerator.weatherapp.data.remote.dto.toWeather
import com.csappgenerator.weatherapp.domain.model.Weather
import com.csappgenerator.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetWeatherDataUseCase @Inject constructor(private val repository: WeatherRepository) {
    suspend operator fun invoke(cityName: String): Weather = withContext(Dispatchers.IO) {
        repository.getWeatherData(cityName = cityName).toWeather()
    }
}

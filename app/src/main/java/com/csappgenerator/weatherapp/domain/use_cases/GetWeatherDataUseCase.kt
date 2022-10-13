package com.csappgenerator.weatherapp.domain.use_cases


import com.csappgenerator.weatherapp.common.Exceptions
import com.csappgenerator.weatherapp.common.Resource
import com.csappgenerator.weatherapp.domain.model.Weather
import com.csappgenerator.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWeatherDataUseCase @Inject constructor(private val repository: WeatherRepository) {

    operator fun invoke(): Flow<Resource<List<Weather>>> = flow {
        emit(Resource.Loading())
        try {
            val weatherList = repository.getWeatherData()
            if (weatherList.isEmpty()) {
                throw Exceptions.NoDataFoundException("No data found!")
            }
            emit(Resource.Success(weatherList))
        } catch (e: Exception) {
            emit(Resource.Error(e.message!!))
        }
    }
}


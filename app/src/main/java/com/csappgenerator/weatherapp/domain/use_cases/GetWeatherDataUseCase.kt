package com.csappgenerator.weatherapp.domain.use_cases


import com.csappgenerator.weatherapp.common.Resource
import com.csappgenerator.weatherapp.domain.model.Weather
import com.csappgenerator.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWeatherDataUseCase @Inject constructor(private val repository: WeatherRepository) {

    operator fun invoke(): Flow<Resource<List<Weather>>> = flow {
        emit(Resource.Loading())
        val remoteList = repository.getWeatherData()
        if (remoteList.isNotEmpty()) {
            repository.deleteAll()
            repository.insertAll(remoteList.map {
                it.toWeatherEntity()
            })
        }
        val weatherListFromDb = repository.getWeatherDataFromDb().map {
            it.toWeather()
        }
        if (weatherListFromDb.isEmpty()) {
            emit(Resource.Error(message = "No data found!"))
            return@flow
        }
        emit(Resource.Success(weatherListFromDb))
    }
}


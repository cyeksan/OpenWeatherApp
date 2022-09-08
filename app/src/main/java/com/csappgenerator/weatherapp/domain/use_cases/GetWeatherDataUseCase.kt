package com.csappgenerator.weatherapp.domain.use_cases


import com.csappgenerator.weatherapp.common.City
import com.csappgenerator.weatherapp.common.CityKeys
import com.csappgenerator.weatherapp.common.Resource
import com.csappgenerator.weatherapp.data.remote.dto.toWeather
import com.csappgenerator.weatherapp.domain.model.Weather
import com.csappgenerator.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.supervisorScope
import timber.log.Timber
import javax.inject.Inject

class GetWeatherDataUseCase @Inject constructor(private val repository: WeatherRepository) {
    private val cities = City.getCities()

    operator fun invoke(): Flow<Resource<List<Weather>>> = flow {
        emit(Resource.Loading())
        val list = mutableListOf<Weather>()
        supervisorScope {
            val deferredGothenburg = async {
                repository.getWeatherData(cityName = cities[CityKeys.GOTHENBURG.name]!!).toWeather()
            }
            val deferredStockholm = async {
                repository.getWeatherData(cityName = cities[CityKeys.STOCKHOLM.name]!!).toWeather()
            }
            val deferredMountainView = async {
                repository.getWeatherData(cityName = cities[CityKeys.MOUNTAIN_VIEW.name]!!)
                    .toWeather()
            }
            val deferredLondon = async {
                repository.getWeatherData(cityName = cities[CityKeys.LONDON.name]!!).toWeather()
            }
            val deferredNewYork = async {
                repository.getWeatherData(cityName = cities[CityKeys.NEW_YORK.name]!!).toWeather()
            }
            val deferredBerlin = async {
                repository.getWeatherData(cityName = cities[CityKeys.BERLIN.name]!!).toWeather()
            }
            val deferredList = listOf(
                deferredGothenburg,
                deferredStockholm,
                deferredMountainView,
                deferredLondon,
                deferredNewYork,
                deferredBerlin
            )

            deferredList.forEach { deferredResult ->
                val result = try {
                    deferredResult.await()

                } catch (exception: Exception) {
                    Timber.e(exception.message)
                    null
                }
                if (result != null) {
                    list.add(result)
                }
            }
            if (list.isEmpty()) {
                emit(Resource.Error(message = "No data found"))
                return@supervisorScope
            }
            emit(Resource.Success(list))
        }
    }
}

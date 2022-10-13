package com.csappgenerator.weatherapp.data.repository

import com.csappgenerator.weatherapp.common.City
import com.csappgenerator.weatherapp.common.CityKeys
import com.csappgenerator.weatherapp.data.local.WeatherDao
import com.csappgenerator.weatherapp.data.local.entity.WeatherEntity
import com.csappgenerator.weatherapp.data.remote.WeatherApi
import com.csappgenerator.weatherapp.data.remote.dto.toWeather
import com.csappgenerator.weatherapp.domain.model.Weather
import com.csappgenerator.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.supervisorScope
import timber.log.Timber
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
    private val dao: WeatherDao
) : WeatherRepository {
    private val cities = City.getCities()

    override suspend fun getWeatherData(): List<Weather> {
        var deferredList = emptyList<Weather>()
        supervisorScope {
            val deferredGothenburg = async {
                api.getWeatherData(cityName = cities[CityKeys.GOTHENBURG.name]!!).toWeather()
            }
            val deferredStockholm = async {
                api.getWeatherData(cityName = cities[CityKeys.STOCKHOLM.name]!!).toWeather()
            }
            val deferredMountainView = async {
                api.getWeatherData(cityName = cities[CityKeys.MOUNTAIN_VIEW.name]!!).toWeather()
            }
            val deferredLondon = async {
                api.getWeatherData(cityName = cities[CityKeys.LONDON.name]!!).toWeather()
            }
            val deferredNewYork = async {
                api.getWeatherData(cityName = cities[CityKeys.NEW_YORK.name]!!).toWeather()
            }
            val deferredBerlin = async {
                api.getWeatherData(cityName = cities[CityKeys.BERLIN.name]!!).toWeather()
            }

            deferredList = listOf(
                deferredGothenburg,
                deferredStockholm,
                deferredMountainView,
                deferredLondon,
                deferredNewYork,
                deferredBerlin
            ).mapNotNull { deferredResult ->
                try {
                    deferredResult.await()
                } catch (exception: Exception) {
                    Timber.e(exception.message)
                    null
                }
            }
        }
        return deferredList
    }

    override suspend fun getWeatherDataFromDb(): List<WeatherEntity> {
        return dao.getWeatherDataFromDb()
    }

    override suspend fun insertAll(weatherList: List<WeatherEntity>) {
        dao.insertAll(weatherList)
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }
}
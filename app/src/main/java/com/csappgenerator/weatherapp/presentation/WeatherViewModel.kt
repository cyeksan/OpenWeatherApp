package com.csappgenerator.weatherapp.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csappgenerator.weatherapp.common.City
import com.csappgenerator.weatherapp.common.CityKeys
import com.csappgenerator.weatherapp.common.Resource
import com.csappgenerator.weatherapp.domain.model.Weather
import com.csappgenerator.weatherapp.domain.use_cases.GetWeatherDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherDataUseCase: GetWeatherDataUseCase
) : ViewModel() {
    private val _state = mutableStateOf<Resource<List<Weather>>>(Resource.Loading(null))
    val state: State<Resource<List<Weather>>> = _state

    private val cities = City.getCities()

    init {
        getCurrentWeatherData()
    }

    private fun getCurrentWeatherData() {
        val weatherList = mutableListOf<Weather>()

        viewModelScope.launch {
            supervisorScope {
                // All weather values are fetched asynchronously in different coroutines.
                val deferredGothenburg = async {
                    withContext(Dispatchers.IO) {
                        getWeatherDataUseCase(cities[CityKeys.GOTHENBURG.name]!!)
                    }
                }
                val deferredStockholm = async {
                    withContext(Dispatchers.IO) {
                        getWeatherDataUseCase(cities[CityKeys.STOCKHOLM.name]!!)
                    }
                }
                val deferredMountainView = async {
                    withContext(Dispatchers.IO) {
                        getWeatherDataUseCase(cities[CityKeys.MOUNTAIN_VIEW.name]!!)
                    }
                }

                val deferredLondon = async {
                    withContext(Dispatchers.IO) {
                        getWeatherDataUseCase(cities[CityKeys.LONDON.name]!!)
                    }
                }

                val deferredNewYork = async {
                    withContext(Dispatchers.IO) {
                        cities[CityKeys.NEW_YORK.name]?.let { cityName ->
                            getWeatherDataUseCase(cityName)
                        }
                    }
                }
                val deferredBerlin = async {
                    withContext(Dispatchers.IO) {
                        getWeatherDataUseCase(cities[CityKeys.BERLIN.name]!!)
                    }
                }
                val exceptionNumber = weatherList.addConvertedValuesFromDeferred(
                    deferredGothenburg,
                    deferredStockholm,
                    deferredMountainView,
                    deferredLondon,
                    deferredNewYork,
                    deferredBerlin
                )
                if (exceptionNumber == cities.size) {
                    // If all the tasks fail, there must be a problem e.g. http connection exception or or internal server error:
                    _state.value =
                        Resource.Error(message = "No data found. Please check your internet connection!")
                    return@supervisorScope
                }

                // In case only some of the async tasks fail, the others won't be affected:
                _state.value = Resource.Success(data = weatherList)
            }
        }
    }
}

suspend fun MutableList<Weather>.addConvertedValuesFromDeferred(
    deferredGothenburg: Deferred<Weather?>,
    deferredStockholm: Deferred<Weather?>,
    deferredMountainView: Deferred<Weather?>,
    deferredLondon: Deferred<Weather?>,
    deferredNewYork: Deferred<Weather?>,
    deferredBerlin: Deferred<Weather?>,
): Int {
    var exceptionNumber = 0

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
            exceptionNumber++
            null
        }
        if (result != null) {
            this.add(result)
        }
    }
    return exceptionNumber
}



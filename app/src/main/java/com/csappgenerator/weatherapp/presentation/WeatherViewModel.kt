package com.csappgenerator.weatherapp.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csappgenerator.weatherapp.common.Resource
import com.csappgenerator.weatherapp.domain.use_cases.GetWeatherDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherDataUseCase: GetWeatherDataUseCase
) : ViewModel() {
    private val _state = mutableStateOf(WeatherState())
    val state: State<WeatherState> = _state


    init {
        getCurrentWeatherData()
    }

    private fun getCurrentWeatherData() {

        getWeatherDataUseCase()
            .onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = WeatherState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _state.value = WeatherState(weatherList = result.data!!)
                    }
                    is Resource.Error -> {
                        _state.value = WeatherState(error = result.message!!)
                    }
                }
            }
            .flowOn(Dispatchers.Main)
            .launchIn(viewModelScope)
    }
}



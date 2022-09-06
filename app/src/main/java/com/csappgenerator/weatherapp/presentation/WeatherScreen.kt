package com.csappgenerator.weatherapp.presentation


import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.csappgenerator.weatherapp.R
import com.csappgenerator.weatherapp.common.*
import com.csappgenerator.weatherapp.common.Constants.DEGREE
import com.csappgenerator.weatherapp.common.Constants.DISTANCE_UNIT
import com.csappgenerator.weatherapp.common.Constants.FLOW_UNIT
import com.csappgenerator.weatherapp.common.Constants.PERCENTAGE
import com.csappgenerator.weatherapp.common.Constants.PRESSURE_UNIT
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

@OptIn(ExperimentalPagerApi::class)
@Composable
fun GetWeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel()

) {
    val state = viewModel.state.value
    val context = LocalContext.current

    Scaffold(
        topBar = {}
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (state) {
                is Resource.Loading -> {
                    CircularProgressIndicator()
                }
                is Resource.Success -> {
                    val weatherList = state.data
                    weatherList?.size?.let { itemNumber ->
                        HorizontalPager(
                            count = itemNumber,
                            contentPadding = PaddingValues(start = 0.dp),
                        ) { item ->
                            val weatherItem = state.data[item]
                            weatherItem.weather?.get(0)?.let { weatherDetail ->
                                WeatherContent(
                                    location = weatherItem.name.toString(),
                                    temperature = "${weatherItem.main?.temp?.toCelsius()}$DEGREE",
                                    icon = weatherDetail.icon,
                                    description = weatherDetail.description,
                                    humidity = "${weatherItem.main?.humidity.toString()}$PERCENTAGE",
                                    clouds = "${weatherItem.clouds?.all?.toString() ?: 0}$PERCENTAGE",
                                    visibility = "${weatherItem.visibility?.toKilometer()} $DISTANCE_UNIT",
                                    wind = "${weatherItem.wind?.speed.toKilometerPerHour()} $FLOW_UNIT",
                                    direction = "${weatherItem.wind?.deg.toDirection()}",
                                    pressure = "${weatherItem.main?.pressure} $PRESSURE_UNIT",
                                )
                            }
                        }
                    }
                }

                is Resource.Error -> {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        modifier = Modifier.size(64.dp),
                        contentDescription = stringResource(R.string.warning_icon)
                    )
                    Text(stringResource(R.string.no_data_found))
                    Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
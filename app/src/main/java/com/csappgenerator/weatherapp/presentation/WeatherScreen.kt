package com.csappgenerator.weatherapp.presentation


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
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
        topBar = {},
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                state.let { weatherState ->
                    if (weatherState.isLoading) {
                        CircularProgressIndicator()
                    }

                    if (weatherState.error.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            modifier = Modifier.size(64.dp),
                            contentDescription = stringResource(R.string.warning_icon)
                        )
                        Text(weatherState.error)
                        Toast.makeText(context, R.string.no_data_found, Toast.LENGTH_LONG).show()
                    }
                    HorizontalPager(
                        count = weatherState.weatherList.size,
                        contentPadding = it,
                    ) { item ->
                        val weatherItem = state.weatherList[item]
                        WeatherContent(
                            location = weatherItem.name.toString(),
                            temperature = "${weatherItem.temp?.toCelsius()}${Constants.DEGREE}",
                            icon = weatherItem.icon!!,
                            description = weatherItem.description!!,
                            humidity = "${weatherItem.humidity.toString()}${Constants.PERCENTAGE}",
                            clouds = "${weatherItem.all?.toString() ?: 0}${Constants.PERCENTAGE}",
                            visibility = "${weatherItem.visibility?.toKilometer()} ${Constants.DISTANCE_UNIT}",
                            wind = "${weatherItem.speed.toKilometerPerHour()} ${Constants.FLOW_UNIT}",
                            direction = "${weatherItem.deg.toDirection()}",
                            pressure = "${weatherItem.pressure} ${Constants.PRESSURE_UNIT}",
                        )

                    }
                }
            }
        }
    )
}


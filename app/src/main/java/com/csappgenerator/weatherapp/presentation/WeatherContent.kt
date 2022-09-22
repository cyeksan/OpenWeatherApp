package com.csappgenerator.weatherapp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csappgenerator.weatherapp.presentation.composable.BottomCard
import com.csappgenerator.weatherapp.presentation.composable.TopCard
import com.csappgenerator.weatherapp.presentation.composable.TopInfo


@Composable
fun WeatherContent(
    modifier: Modifier,
    location: String,
    temperature: String,
    icon: String,
    description: String,
    humidity: String,
    clouds: String,
    visibility: String,
    wind: String,
    direction: String,
    pressure: String
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {

        TopInfo(
            location = location,
            temperature = temperature,
            icon = icon,
            description = description
        )
        TopCard(
            humidity = humidity,
            clouds = clouds,
            visibility = visibility
        )
        BottomCard(
            wind = wind,
            direction = direction,
            pressure = pressure
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WeatherContentPreview() {
    WeatherContent(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        location = "London",
        temperature = "25°",
        icon = "10d",
        description = "Overcast clouds",
        humidity = "72%",
        clouds = "40",
        visibility = "10.00km",
        wind = "11.16 km/h",
        direction = "NE",
        pressure = "1024 hPa",
    )
}
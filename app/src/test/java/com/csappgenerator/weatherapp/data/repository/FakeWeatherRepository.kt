package com.csappgenerator.weatherapp.data.repository

import com.csappgenerator.weatherapp.data.remote.dto.WeatherDto
import com.csappgenerator.weatherapp.data.remote.dto.toWeather
import com.csappgenerator.weatherapp.domain.model.Weather
import com.csappgenerator.weatherapp.domain.repository.WeatherRepository
import com.google.gson.Gson

class FakeWeatherRepository : WeatherRepository {
    private lateinit var gothenburg: Weather
    private lateinit var stockholm: Weather
    private lateinit var mountainView: Weather
    private lateinit var london: Weather
    private lateinit var newYork: Weather
    private lateinit var berlin: Weather
    private val weatherList = mutableListOf<Weather>()
    override suspend fun getWeatherData(): List<Weather> {

        val jsonGothenburg = """{
    "coord": {
        "lon": 11.9668,
        "lat": 57.7072
    },
    "weather": [
        {
            "id": 802,
            "main": "Clouds",
            "description": "scattered clouds",
            "icon": "03d"
        }
    ],
    "base": "stations",
    "main": {
        "temp": 287.45,
        "feels_like": 286.92,
        "temp_min": 287.07,
        "temp_max": 287.97,
        "pressure": 1025,
        "humidity": 76
    },
    "visibility": 10000,
    "wind": {
        "speed": 2.57,
        "deg": 240
    },
    "clouds": {
        "all": 26
    },
    "dt": 1663753043,
    "sys": {
        "type": 2,
        "id": 2006458,
        "country": "SE",
        "sunrise": 1663736066,
        "sunset": 1663780574
    },
    "timezone": 7200,
    "id": 2711537,
    "name": "Gothenburg",
    "cod": 200
}"""

        val jsonStockholm = """{
    "coord": {
        "lon": 18.0649,
        "lat": 59.3326
    },
    "weather": [
        {
            "id": 800,
            "main": "Clear",
            "description": "clear sky",
            "icon": "01d"
        }
    ],
    "base": "stations",
    "main": {
        "temp": 285.84,
        "feels_like": 284.89,
        "temp_min": 284.15,
        "temp_max": 287.1,
        "pressure": 1022,
        "humidity": 66
    },
    "visibility": 10000,
    "wind": {
        "speed": 1.54,
        "deg": 0
    },
    "clouds": {
        "all": 0
    },
    "dt": 1663754248,
    "sys": {
        "type": 1,
        "id": 1788,
        "country": "SE",
        "sunrise": 1663734564,
        "sunset": 1663779150
    },
    "timezone": 7200,
    "id": 2673730,
    "name": "Stockholm",
    "cod": 200
}"""

        val jsonMountainView = """{
    "coord": {
        "lon": -122.0838,
        "lat": 37.3861
    },
    "weather": [
        {
            "id": 804,
            "main": "Clouds",
            "description": "overcast clouds",
            "icon": "04n"
        }
    ],
    "base": "stations",
    "main": {
        "temp": 289.25,
        "feels_like": 289,
        "temp_min": 287.07,
        "temp_max": 290.76,
        "pressure": 1011,
        "humidity": 80
    },
    "visibility": 10000,
    "wind": {
        "speed": 0.89,
        "deg": 124,
        "gust": 1.34
    },
    "clouds": {
        "all": 100
    },
    "dt": 1663754323,
    "sys": {
        "type": 2,
        "id": 2017352,
        "country": "US",
        "sunrise": 1663768524,
        "sunset": 1663812444
    },
    "timezone": -25200,
    "id": 5375480,
    "name": "Mountain View",
    "cod": 200
}"""
        val jsonLondon = """{
    "coord": {
        "lon": -0.1257,
        "lat": 51.5085
    },
    "weather": [
        {
            "id": 802,
            "main": "Clouds",
            "description": "scattered clouds",
            "icon": "03d"
        }
    ],
    "base": "stations",
    "main": {
        "temp": 289.47,
        "feels_like": 289.06,
        "temp_min": 287.35,
        "temp_max": 292.33,
        "pressure": 1026,
        "humidity": 73
    },
    "visibility": 10000,
    "wind": {
        "speed": 1.54,
        "deg": 100
    },
    "clouds": {
        "all": 37
    },
    "dt": 1663753852,
    "sys": {
        "type": 2,
        "id": 2075535,
        "country": "GB",
        "sunrise": 1663739082,
        "sunset": 1663783361
    },
    "timezone": 3600,
    "id": 2643743,
    "name": "London",
    "cod": 200
}"""
        val jsonNewYork = """{
    "coord": {
        "lon": -74.006,
        "lat": 40.7143
    },
    "weather": [
        {
            "id": 802,
            "main": "Clouds",
            "description": "scattered clouds",
            "icon": "03n"
        }
    ],
    "base": "stations",
    "main": {
        "temp": 290.27,
        "feels_like": 290.05,
        "temp_min": 286.22,
        "temp_max": 292.58,
        "pressure": 1014,
        "humidity": 77
    },
    "visibility": 10000,
    "wind": {
        "speed": 2.06,
        "deg": 340
    },
    "clouds": {
        "all": 40
    },
    "dt": 1663754317,
    "sys": {
        "type": 2,
        "id": 2039034,
        "country": "US",
        "sunrise": 1663756952,
        "sunset": 1663800945
    },
    "timezone": -14400,
    "id": 5128581,
    "name": "New York",
    "cod": 200
}"""
        val jsonBerlin = """{
    "coord": {
        "lon": 13.4105,
        "lat": 52.5244
    },
    "weather": [
        {
            "id": 800,
            "main": "Clear",
            "description": "clear sky",
            "icon": "01d"
        }
    ],
    "base": "stations",
    "main": {
        "temp": 288.05,
        "feels_like": 287.71,
        "temp_min": 285.83,
        "temp_max": 290.99,
        "pressure": 1024,
        "humidity": 81
    },
    "visibility": 10000,
    "wind": {
        "speed": 2.68,
        "deg": 319,
        "gust": 4.47
    },
    "clouds": {
        "all": 0
    },
    "dt": 1663754146,
    "sys": {
        "type": 2,
        "id": 2011538,
        "country": "DE",
        "sunrise": 1663735814,
        "sunset": 1663780134
    },
    "timezone": 7200,
    "id": 2950159,
    "name": "Berlin",
    "cod": 200
}"""
        gothenburg = Gson().fromJson(jsonGothenburg, WeatherDto::class.java).toWeather()
        stockholm = Gson().fromJson(jsonStockholm, WeatherDto::class.java).toWeather()
        mountainView = Gson().fromJson(jsonMountainView, WeatherDto::class.java).toWeather()
        london = Gson().fromJson(jsonMountainView, WeatherDto::class.java).toWeather()
        newYork = Gson().fromJson(jsonNewYork, WeatherDto::class.java).toWeather()
        berlin = Gson().fromJson(jsonBerlin, WeatherDto::class.java).toWeather()
        weatherList.addAll(
            arrayOf(
                gothenburg,
                stockholm,
                mountainView,
                london,
                newYork,
                berlin
            )
        )
        return weatherList

    }
}
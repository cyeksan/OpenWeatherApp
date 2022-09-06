package com.csappgenerator.weatherapp.common

object City {
    fun getCities(): Map<String, String> {
        return mapOf(
            CityKeys.GOTHENBURG.name to "Gothenburg",
            CityKeys.STOCKHOLM.name to "Stockholm",
            CityKeys.MOUNTAIN_VIEW.name to "Mountain View",
            CityKeys.LONDON.name to "London",
            CityKeys.NEW_YORK.name to "New York",
            CityKeys.BERLIN.name to "Berlin"
        )
    }
}
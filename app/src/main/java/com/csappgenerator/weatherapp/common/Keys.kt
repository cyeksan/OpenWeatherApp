package com.csappgenerator.weatherapp.common

object Keys {
    init {
        System.loadLibrary("native-lib")
    }

    external fun getApiKey(apiNumber: Int): String
}
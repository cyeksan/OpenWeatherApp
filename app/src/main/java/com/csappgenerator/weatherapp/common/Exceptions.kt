package com.csappgenerator.weatherapp.common

object Exceptions {
    class NoDataFoundException(message: String) :
        Exception(message)
}
package com.csappgenerator.weatherapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.csappgenerator.weatherapp.data.local.entity.WeatherEntity

@Database(
    entities = [WeatherEntity::class],
    version = 2,
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract val weatherDao: WeatherDao

    companion object {
        const val DATABASE_NAME = "weather_database"
    }
}
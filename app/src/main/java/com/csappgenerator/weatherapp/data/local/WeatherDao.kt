package com.csappgenerator.weatherapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.csappgenerator.weatherapp.data.local.entity.WeatherEntity

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weatherentity")
    suspend fun getWeatherDataFromDb(): List<WeatherEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(weatherList: List<WeatherEntity>)

    @Query("DELETE FROM weatherentity")
    suspend fun deleteAll()
}
package com.csappgenerator.weatherapp.di

import android.app.Application
import androidx.room.Room
import com.csappgenerator.weatherapp.data.local.WeatherDatabase
import com.csappgenerator.weatherapp.data.remote.WeatherApi
import com.csappgenerator.weatherapp.data.repository.WeatherRepositoryImpl
import com.csappgenerator.weatherapp.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideWeatherDatabase(application: Application): WeatherDatabase {
        return Room.databaseBuilder(
            application,
            WeatherDatabase::class.java,
            WeatherDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(WeatherApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: WeatherApi, db: WeatherDatabase): WeatherRepository {
        return WeatherRepositoryImpl(api, db.weatherDao)
    }
}
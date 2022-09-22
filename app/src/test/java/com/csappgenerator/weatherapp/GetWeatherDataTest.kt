package com.csappgenerator.weatherapp


import com.csappgenerator.weatherapp.common.City
import com.csappgenerator.weatherapp.common.Resource
import com.csappgenerator.weatherapp.data.repository.FakeWeatherRepository
import com.csappgenerator.weatherapp.domain.use_cases.GetWeatherDataUseCase
import org.junit.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetWeatherDataTest {
    private lateinit var fakeWeatherRepository: FakeWeatherRepository
    private lateinit var getWeatherDataUseCase: GetWeatherDataUseCase

    private lateinit var cities: Collection<String>

    @Before
    fun setUp() {
        cities = City.getCities().values
        fakeWeatherRepository = FakeWeatherRepository()
        getWeatherDataUseCase = GetWeatherDataUseCase(fakeWeatherRepository)
    }

    @Test
    fun `check if all city names are correct`() = runTest {
        getWeatherDataUseCase().collect { result ->
            if (result is Resource.Success) {
                result.data?.forEach {
                    assertEquals(cities.toList()[cities.indexOf(it.name)], it.name)
                }
            }
        }
    }
}

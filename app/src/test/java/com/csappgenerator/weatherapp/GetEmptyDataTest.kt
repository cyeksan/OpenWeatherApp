package com.csappgenerator.weatherapp

import com.csappgenerator.weatherapp.common.City
import com.csappgenerator.weatherapp.common.Resource
import com.csappgenerator.weatherapp.data.repository.FakeEmptyDataRepository
import com.csappgenerator.weatherapp.domain.use_cases.GetWeatherDataUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetEmptyDataTest {
    private lateinit var fakeEmptyDataRepository: FakeEmptyDataRepository
    private lateinit var getWeatherDataUseCase: GetWeatherDataUseCase

    private lateinit var cities: Collection<String>

    @Before
    fun setUp() {
        cities = City.getCities().values
        fakeEmptyDataRepository = FakeEmptyDataRepository()
        getWeatherDataUseCase = GetWeatherDataUseCase(fakeEmptyDataRepository)
    }

    @Test
    fun `check if error message in case of empty list is correct`() = runTest {
        getWeatherDataUseCase().collect { result ->
            if (result is Resource.Error) {
                assertEquals("No data found!", result.message)
            }
        }
    }
}

package com.csappgenerator.weatherapp

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.csappgenerator.weatherapp.common.City
import com.csappgenerator.weatherapp.di.AppModule
import com.csappgenerator.weatherapp.presentation.GetWeatherScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class EmptyDataScreenTest {
    private lateinit var cities: Collection<String>
    private var citySize: Int = 0


    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.activity.setContent {
            GetWeatherScreen()
        }
        cities = City.getCities().values
        citySize = cities.size
    }

    @Test
    fun showErrorIcon() {
        // In case weather repository returns empty list, it displays error icon:
        composeRule.onNodeWithContentDescription("Warning icon").assertIsDisplayed()
    }
}
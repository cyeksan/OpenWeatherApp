package com.csappgenerator.weatherapp

import androidx.activity.compose.setContent
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.espresso.IdlingRegistry
import com.csappgenerator.weatherapp.common.City
import com.csappgenerator.weatherapp.common.TestTags
import com.csappgenerator.weatherapp.di.AppModule
import com.csappgenerator.weatherapp.presentation.GetWeatherScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import timber.log.Timber


@HiltAndroidTest
@UninstallModules(AppModule::class)
class WeatherScreenTest {
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

        // In order to solve Idling resource timed out exception in bundle test runs:
        IdlingRegistry.getInstance().resources.forEach {
            Timber.e("resource ${it.name}    idleNow: ${it.isIdleNow}")
            if (it.name == "Compose-Espresso link") {
                IdlingRegistry.getInstance().unregister(it)
            }
        }
    }

    @Test
    fun showAllWeatherDataCorrectly() {
        repeat(citySize - 1) {
            composeRule.onNodeWithTag(TestTags.getPageTag(it)).assertIsDisplayed()
            composeRule.onRoot().performTouchInput {
                swipeLeft(durationMillis = 1000)
            }
        }
    }

    @Test
    fun swipingLeftOnThePageOpensAllCorrectPages() {
        repeat(citySize - 1) {
            composeRule.onAllNodesWithTag("location")
                .filterToOne(hasTextExactly(cities.toList()[it]))
                .assertIsDisplayed()
            composeRule.onRoot().performTouchInput {
                swipeLeft(durationMillis = 1000)
            }
        }
    }

    @Test
    fun maxCityNumberIs6() {
        val citySize = cities.size
        repeat(citySize) {
            composeRule.onRoot().performTouchInput {
                swipeLeft(durationMillis = 1000)
            }
        }
        composeRule.onAllNodesWithTag("location")
            .filterToOne(hasTextExactly(cities.toList()[citySize - 1]))
            .assertIsDisplayed()
    }

    @Test
    fun swipingRightOnThePageOpensCorrectPage() {
        repeat(citySize - 1) {
            composeRule.onRoot().performTouchInput {
                swipeLeft(durationMillis = 1000)
            }
        }
        composeRule.onRoot().performTouchInput {
            swipeRight(durationMillis = 1000)
        }
        composeRule.onAllNodesWithTag("location")
            .filterToOne(hasTextExactly(cities.toList()[citySize - 2]))
            .assertIsDisplayed()
    }

}
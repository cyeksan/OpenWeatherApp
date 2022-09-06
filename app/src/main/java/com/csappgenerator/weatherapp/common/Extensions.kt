package com.csappgenerator.weatherapp.common

import com.csappgenerator.weatherapp.common.Constants.ABSOLUTE_TEMP
import com.csappgenerator.weatherapp.common.Constants.KM_CONVERSION_COEFFICIENT
import com.csappgenerator.weatherapp.common.Constants.KM_PER_HOUR_CONVERSION_COEFFICIENT
import kotlin.math.roundToInt

fun Double?.toCelsius(): Int? {
    return (this?.plus(ABSOLUTE_TEMP))?.roundToInt()
}

fun Int?.toKilometer(): String {
    return String.format(
        "%.2f",
        (this?.toDouble()?.div(KM_CONVERSION_COEFFICIENT)?.times(100.0))?.roundToInt()?.div(100.0)
            ?: 0.00
    )
}

fun Double?.toKilometerPerHour(): String {
    return String.format(
        "%.2f", (this?.times(KM_PER_HOUR_CONVERSION_COEFFICIENT))
    )
}

fun Int?.toDirection(): String? {
    when (this) {
        in 350..360 -> {
            return Directions.N.name
        }
        in 0..11 -> {
            return Directions.N.name
        }
        in 12..34 -> {
            return Directions.NNE.name
        }
        in 35..56 -> {
            return Directions.NE.name
        }
        in 57..78 -> {
            return Directions.ENE.name
        }
        in 80..101 -> {
            return Directions.E.name
        }
        in 102..124 -> {
            return Directions.ESE.name
        }
        in 123..146 -> {
            return Directions.SE.name
        }
        in 147..169 -> {
            return Directions.SSE.name
        }
        in 170..191 -> {
            return Directions.S.name
        }
        in 192..214 -> {
            return Directions.SSW.name
        }
        in 215..236 -> {
            return Directions.SW.name
        }
        in 237..259 -> {
            return Directions.WSW.name
        }
        in 260..281 -> {
            return Directions.W.name
        }
        in 282..304 -> {
            return Directions.WNW.name
        }
        in 305..326 -> {
            return Directions.NW.name
        }
        in 327..349 -> {
            return Directions.NNW.name
        }
    }
    return null
}

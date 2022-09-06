package com.csappgenerator.weatherapp.presentation.ui.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

val Blue200 = Color(0xFF536DFE)
val Blue500 = Color(0xFF3F51B5)
val Blue700 = Color(0xFFFF303F9F)
val Orange700 = Color(0xFFFF57C00)
val ShadowedBlue = Color(0xFF405384)
val LightShadowedBlue = Color(0xFF556593)
val White = Color(0xFFFFFFFF)

val Colors.cardColor: Color
    get() = if (isLight) Color.White else LightShadowedBlue

val Colors.dividerColor: Color
    get() = if (isLight) Color.LightGray else White


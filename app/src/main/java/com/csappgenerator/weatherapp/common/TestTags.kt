package com.csappgenerator.weatherapp.common

object TestTags {

    private const val pager = "dynamic-pager"
    const val location = "location"
    fun getPageTag(index: Int) = "$pager-$index"
}
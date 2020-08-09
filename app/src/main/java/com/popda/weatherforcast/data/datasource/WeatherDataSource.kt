package com.popda.weatherforcast.data.datasource

import android.content.Context
import com.popda.weatherforcast.data.entity.BaseResponse

interface WeatherDataSource {

    fun  getWeatherByGeo(lat: Double, long: Double) : BaseResponse

    fun getWeatherByCityName(cityName: String, context: Context) : BaseResponse
}
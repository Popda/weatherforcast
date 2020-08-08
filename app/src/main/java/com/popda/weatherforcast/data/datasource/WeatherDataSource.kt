package com.popda.weatherforcast.data.datasource

import com.popda.weatherforcast.data.entity.BaseResponse

interface WeatherDataSource {

    fun  getWeatherByGeo(lat: Int, long: Int) : BaseResponse

    fun getWeatherByCityName(cityName: String) : BaseResponse
}
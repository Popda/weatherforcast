package com.popda.weatherforcast.data.datasource

import android.content.Context
import com.popda.weatherforcast.data.entity.BaseResponse
import retrofit2.Call

interface WeatherDataSource {

    fun  getWeatherByGeo(lat: Double, long: Double) : Call<BaseResponse>

    fun getWeatherByCityName(cityName: String, context: Context) : Call<BaseResponse>

}
package com.popda.weatherforcast.data

import com.popda.weatherforcast.BuildConfig
import com.popda.weatherforcast.data.entity.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("onecall")
    fun getWeatherByGeoCords(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("exclude") exclude: String = "hourly,minutely",
        @Query("units") units : String = "metric",
        @Query("appid") appId: String = BuildConfig.API_KEY
    ) : Call<BaseResponse>
}
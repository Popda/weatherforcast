package com.popda.weatherforcast.data

import com.popda.weatherforcast.data.entity.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("daily")
    fun getWeatherByGeoCords(
        @Query("lat") lat: Int,
        @Query("lon") lon: Int,
        @Query("cnt") cnt: Int,
        @Query("appid") appId: String = "photo"
    ) : BaseResponse

    @GET("daily")
    fun getWeatherByCityName(
        @Query("q") cityName: String,
        @Query("cnt") cnt: Int,
        @Query("appid") appId: String = "photo"
    ) : BaseResponse
}
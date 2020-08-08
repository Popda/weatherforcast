package com.popda.weatherforcast.data.datasource

import com.popda.weatherforcast.BuildConfig
import com.popda.weatherforcast.data.WeatherApi
import com.popda.weatherforcast.data.entity.BaseResponse
import javax.inject.Inject

class WeatherRemoteDataSource @Inject constructor(private val  api : WeatherApi) : WeatherDataSource {
    override fun getWeatherByGeo(lat: Int, long: Int): BaseResponse {
        return api.getWeatherByGeoCords(lat, long, 16, BuildConfig.API_KEY)
    }

    override fun getWeatherByCityName(cityName: String): BaseResponse {
        return api.getWeatherByCityName(cityName, 16, BuildConfig.API_KEY)
    }

}
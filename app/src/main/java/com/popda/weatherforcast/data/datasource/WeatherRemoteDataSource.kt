package com.popda.weatherforcast.data.datasource

import android.content.Context
import android.location.Geocoder
import com.popda.weatherforcast.data.WeatherApi
import com.popda.weatherforcast.data.entity.BaseResponse
import javax.inject.Inject

class WeatherRemoteDataSource @Inject constructor(private val  api : WeatherApi) : WeatherDataSource {

    override fun getWeatherByGeo(lat: Double, long: Double): BaseResponse {
        return api.getWeatherByGeoCords(lat, long)
    }

    override fun getWeatherByCityName(cityName: String, context: Context): BaseResponse {
        val latLong = getCityGeo(context, cityName)
        return getWeatherByGeo(latLong.first, latLong.second)
    }

    private fun getCityGeo(context: Context, cityName: String) : Pair<Double, Double>{
        var latLong = Pair(0.0, 0.0)
        Geocoder(context).getFromLocationName(cityName, 1).forEach {
           latLong = Pair(it.latitude, it.longitude)
        }
        return latLong
    }

}
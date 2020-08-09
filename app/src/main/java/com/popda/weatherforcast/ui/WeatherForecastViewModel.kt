package com.popda.weatherforcast.ui

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.*
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.LocationServices
import com.popda.weatherforcast.data.datasource.WeatherRemoteDataSource
import com.popda.weatherforcast.data.entity.WeatherForecast

import javax.inject.Inject


class WeatherForecastViewModel @Inject constructor(private val weatherDataSource: WeatherRemoteDataSource) : ViewModel() {
    private var currCity = ""
    private lateinit var curLatLong: Pair<Double, Double>

    private val mutableWeatherLiveData: MutableLiveData<List<WeatherForecast>> = MutableLiveData()
    val weatherLiveData: LiveData<List<WeatherForecast>> get() = mutableWeatherLiveData

    fun loadWeatherForecast(activity: Activity){
        when {
            currCity.isNotBlank() -> weatherDataSource.getWeatherByCityName(currCity, activity)
            ::curLatLong.isInitialized -> weatherDataSource.getWeatherByGeo(curLatLong.first, curLatLong.second)
            else -> Log.e("WeatherForecastViewModel", "No data to search for weather")
        }
    }

    fun getCurrLocation(activity: Activity){
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
        if (ActivityCompat.checkSelfPermission(
                activity, Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                activity, Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                location?.let {
                    curLatLong = Pair(it.latitude, it.longitude)
                    loadWeatherForecast(activity)
                }
            }
    }
}
package com.popda.weatherforcast.ui.weather_screen

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
import com.popda.weatherforcast.data.entity.BaseResponse
import com.popda.weatherforcast.data.entity.WeatherForecast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import javax.inject.Inject

private val TAG = WeatherForecastViewModel::class.simpleName

class WeatherForecastViewModel @Inject constructor(private val weatherDataSource: WeatherRemoteDataSource) : ViewModel() {
    var currCity = ""
    private lateinit var curLatLong: Pair<Double, Double>
    val callback = object : Callback<BaseResponse> {
        override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
            Log.e(TAG, "getWeatherByCityName failed: ${t.message}")
        }

        override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
            response.body()?.apply {
                mutableWeatherLiveData.value = daily
                Log.d(TAG, "response = $this")
            }
        }
    }

    private val mutableWeatherLiveData: MutableLiveData<List<WeatherForecast>> = MutableLiveData()
    val weatherLiveData: LiveData<List<WeatherForecast>> get() = mutableWeatherLiveData

    private fun loadWeatherForecast(activity: Activity){
        when {
            currCity.isNotBlank() -> weatherDataSource.getWeatherByCityName(currCity, activity).enqueue(callback)
            ::curLatLong.isInitialized -> weatherDataSource.getWeatherByGeo(curLatLong.first, curLatLong.second).enqueue(callback)
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
                if (location!=null) location.let {
                    curLatLong = Pair(it.latitude, it.longitude)
                    currCity = Geocoder(activity).getFromLocation(it.latitude, it.longitude, 1)[0].locality
                    loadWeatherForecast(activity)
                } else getCurrLocation(activity)
            }
    }
}
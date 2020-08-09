package com.popda.weatherforcast.ui.search_screen

import android.app.Activity
import android.location.Geocoder
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.popda.weatherforcast.data.datasource.CityNameDbSource
import com.popda.weatherforcast.data.entity.CityEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

private val TAG = SearchViewModel::class.simpleName

class SearchViewModel @Inject constructor(private val cityDataSource: CityNameDbSource) : ViewModel() {
    val cityLiveData: LiveData<List<CityEntity>>
        get() { return cityDataSource.cityDao().getAll() }

    fun searchNewLocation(name:String, activity: Activity) : String? {
        val list = Geocoder(activity).getFromLocationName(name, 1)
        return if (list.isNotEmpty()) list[0].locality else null
    }

    fun addCityToFavorite(cityName: String, activity: Activity){
        searchNewLocation(cityName, activity)?.let {
            GlobalScope.launch { cityDataSource.cityDao().addToFav(CityEntity(it)) }
        }
    }

    fun deleteCity(cityEntity: CityEntity){
        GlobalScope.launch { cityDataSource.cityDao().delete(cityEntity)}
    }
}
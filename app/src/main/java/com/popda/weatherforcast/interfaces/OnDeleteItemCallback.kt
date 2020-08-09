package com.popda.weatherforcast.interfaces

import com.popda.weatherforcast.data.entity.CityEntity

interface OnDeleteItemCallback {
    fun onItemDelete(cityEntity: CityEntity)
}
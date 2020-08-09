package com.popda.weatherforcast.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CityEntity (
    @PrimaryKey
    @ColumnInfo(name = "city_name")
    val cityName: String
)
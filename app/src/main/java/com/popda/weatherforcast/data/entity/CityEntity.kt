package com.popda.weatherforcast.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CityEntity (
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "city_name") val cityName: String?
)
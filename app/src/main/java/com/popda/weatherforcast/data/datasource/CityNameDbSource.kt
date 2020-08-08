package com.popda.weatherforcast.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.popda.weatherforcast.data.CityDao
import com.popda.weatherforcast.data.entity.CityEntity

@Database(entities = [CityEntity::class], version = 1)
abstract class CityNameDbSource : RoomDatabase(){
    abstract fun cityDao(): CityDao
}
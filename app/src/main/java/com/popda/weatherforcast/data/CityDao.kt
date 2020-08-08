package com.popda.weatherforcast.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.popda.weatherforcast.data.entity.CityEntity

@Dao
interface CityDao {
    @Query("SELECT * FROM cityentity")
    fun getAll(): List<CityEntity>

    @Query("SELECT * FROM cityentity WHERE city_name LIKE :name LIMIT 1")
    fun findByName(name: String) : CityEntity

    @Query("SELECT * FROM cityentity WHERE uid LIKE :id LIMIT 1")
    fun findById(id: String) : CityEntity

    @Insert
    fun addToFav(cityEntity: CityEntity)

    @Delete
    fun delete(cityEntity: CityEntity)
}
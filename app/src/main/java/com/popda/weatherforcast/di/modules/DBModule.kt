package com.popda.weatherforcast.di.modules

import android.content.Context
import androidx.room.Room
import com.popda.weatherforcast.data.datasource.CityNameDbSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DBModule {

    @Singleton
    @Provides
    fun provideDbInstance(context: Context) : CityNameDbSource{
        return Room.databaseBuilder(
            context,
            CityNameDbSource::class.java, "database-name"
        ).build()
    }
}
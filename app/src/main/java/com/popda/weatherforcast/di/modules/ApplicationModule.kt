package com.popda.weatherforcast.di.modules

import android.content.Context
import com.popda.weatherforcast.WeatherApplication
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ApplicationModule {

    @Binds
    @Singleton
    abstract fun bindContext(application: WeatherApplication): Context
}
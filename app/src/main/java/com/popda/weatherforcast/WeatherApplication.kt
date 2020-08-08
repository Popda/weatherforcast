package com.popda.weatherforcast

import com.popda.weatherforcast.di.components.DaggerWeatherAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class WeatherApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerWeatherAppComponent
            .builder()
            .application(this)
            .build()
    }
}
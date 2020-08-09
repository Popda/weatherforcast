package com.popda.weatherforcast.di.components

import com.popda.weatherforcast.WeatherApplication
import com.popda.weatherforcast.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ActivityBindingModule::class,
    NetworkModule::class,
    ApplicationModule::class,
    ViewModelFactoryModule::class,
    AndroidSupportInjectionModule::class,
    DBModule::class,
    WeatherForecastModule::class
])
interface WeatherAppComponent: AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: WeatherApplication): Builder

        fun build(): WeatherAppComponent
    }
}
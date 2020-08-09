package com.popda.weatherforcast.di.modules

import com.popda.weatherforcast.di.scopes.ActivityScoped
import com.popda.weatherforcast.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [WeatherForecastModule::class])
    abstract fun mainActivity(): MainActivity


}
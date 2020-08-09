package com.popda.weatherforcast.di.modules

import androidx.lifecycle.ViewModelProvider
import com.popda.weatherforcast.di.DaggerViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory):
            ViewModelProvider.Factory
}
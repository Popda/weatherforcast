package com.popda.weatherforcast.di.modules

import androidx.lifecycle.ViewModel
import com.popda.weatherforcast.di.ViewModelKey
import com.popda.weatherforcast.di.scopes.FragmentScoped
import com.popda.weatherforcast.ui.WeatherForecastFragment
import com.popda.weatherforcast.ui.WeatherForecastViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class WeatherForecastModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributesWeatherFragment(): WeatherForecastFragment

    @Binds
    @IntoMap
    @ViewModelKey(WeatherForecastViewModel::class)
    abstract fun bindWeatherFragmentViewModel(viewModel: WeatherForecastViewModel): ViewModel
}
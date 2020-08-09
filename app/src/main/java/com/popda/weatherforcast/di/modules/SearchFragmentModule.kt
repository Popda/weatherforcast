package com.popda.weatherforcast.di.modules

import androidx.lifecycle.ViewModel
import com.popda.weatherforcast.di.ViewModelKey
import com.popda.weatherforcast.di.scopes.FragmentScoped
import com.popda.weatherforcast.ui.search_screen.SearchFragment
import com.popda.weatherforcast.ui.search_screen.SearchViewModel
import com.popda.weatherforcast.ui.weather_screen.WeatherForecastFragment
import com.popda.weatherforcast.ui.weather_screen.WeatherForecastViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class SearchFragmentModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributesSearchFragment(): SearchFragment

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindWeatherFragmentViewModel(viewModel: SearchViewModel): ViewModel
}
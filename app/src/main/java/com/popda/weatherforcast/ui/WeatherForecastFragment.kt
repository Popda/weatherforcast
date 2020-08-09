package com.popda.weatherforcast.ui

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.popda.weatherforcast.R
import com.popda.weatherforcast.data.entity.WeatherForecast
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.weather_forecast_fragment.*
import javax.inject.Inject

class WeatherForecastFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: WeatherForecastViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(WeatherForecastViewModel::class.java)
        return inflater.inflate(R.layout.weather_forecast_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        listenViewModel()
        viewModel.getCurrLocation(activity as Activity)
    }

    private fun initRecyclerView(){
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = WeatherAdapter()
        }
    }

    private fun updateListWithData(list: List<WeatherForecast>){
        (recyclerView.adapter as WeatherAdapter).setData(list)
    }

    private fun listenViewModel(){
        viewModel.weatherLiveData.observe(viewLifecycleOwner, Observer {
            updateListWithData(it)
        })
    }

}
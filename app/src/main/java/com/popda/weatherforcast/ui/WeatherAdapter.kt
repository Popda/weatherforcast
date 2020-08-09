package com.popda.weatherforcast.ui

import android.icu.text.MeasureFormat
import android.icu.text.MeasureFormat.FormatWidth
import android.icu.util.Measure
import android.icu.util.MeasureUnit
import android.icu.util.ULocale
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.popda.weatherforcast.R
import com.popda.weatherforcast.data.entity.WeatherForecast
import kotlinx.android.synthetic.main.weather_forecast_item.view.*
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private var weatherForecastList = ArrayList<WeatherForecast>()
    var fmt: MeasureFormat = MeasureFormat.getInstance(ULocale.ENGLISH, FormatWidth.SHORT)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.weather_forecast_item, parent, false)
        return WeatherViewHolder(view)
    }

    override fun getItemCount(): Int = weatherForecastList.size

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bindTo(weatherForecastList[position])
    }

    fun setData(newList: List<WeatherForecast>) {
        val diffCallback = ForecastDiffCallBack(weatherForecastList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        weatherForecastList.clear()
        weatherForecastList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindTo(weatherForecast: WeatherForecast){
            itemView.apply {
                weatherIv.load("http://openweathermap.org/img/wn/${weatherForecast.weather[0].icon}@2x.png")
                dateTv.text = LocalDateTime.ofInstant(Instant.ofEpochSecond(weatherForecast.dt), ZoneId.systemDefault()).format(
                    DateTimeFormatter.RFC_1123_DATE_TIME)
                weatherDescriptionTv.text = weatherForecast.weather[0].description
                temperatureTv.text = fmt.format(Measure(weatherForecast.temp.day.toInt(), MeasureUnit.CELSIUS))
                windTv.text = resources.getString(R.string.wind_speed, weatherForecast.wind_speed)
                humidityTv.text =  resources.getString(R.string.humidity, weatherForecast.humidity)
            }
        }
    }
}
package com.popda.weatherforcast.ui

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.popda.weatherforcast.data.entity.WeatherForecast

class ForecastDiffCallBack (private val oldList: List<WeatherForecast>, private val newList: List<WeatherForecast>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return oldList[oldPosition] == newList[newPosition]
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}
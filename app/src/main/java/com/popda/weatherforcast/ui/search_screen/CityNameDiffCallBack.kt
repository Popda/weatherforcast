package com.popda.weatherforcast.ui.search_screen

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.popda.weatherforcast.data.entity.CityEntity
import com.popda.weatherforcast.data.entity.WeatherForecast

class CityNameDiffCallBack (private val oldList: List<CityEntity>, private val newList: List<CityEntity>) : DiffUtil.Callback() {

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
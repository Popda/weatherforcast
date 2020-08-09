package com.popda.weatherforcast.ui.search_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.popda.weatherforcast.R
import com.popda.weatherforcast.data.entity.CityEntity
import com.popda.weatherforcast.interfaces.OnDeleteItemCallback
import kotlinx.android.synthetic.main.city_item.view.*


class FavoriteAdapter(private val cb: OnDeleteItemCallback) : RecyclerView.Adapter<FavoriteAdapter.CityItemViewHolder>() {

    private var cityNames = ArrayList<CityEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.city_item, parent, false)
        return CityItemViewHolder(view)
    }

    override fun getItemCount(): Int = cityNames.size

    override fun onBindViewHolder(holder: CityItemViewHolder, position: Int) {
        holder.bindTo(cityNames[position])
    }

    fun setData(newList: List<CityEntity>) {
        val diffCallback = CityNameDiffCallBack(cityNames, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        cityNames.clear()
        cityNames.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class CityItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindTo(cityItem: CityEntity){
            itemView.apply {
                this.setOnClickListener {
                    cb.onItemDelete(cityItem)
                }
                cityTv.text =  cityItem.cityName
            }
        }
    }
}
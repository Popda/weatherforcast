package com.popda.weatherforcast.ui.search_screen

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.popda.weatherforcast.R
import com.popda.weatherforcast.data.entity.CityEntity
import com.popda.weatherforcast.interfaces.OnDeleteItemCallback
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.search_fragment.*
import javax.inject.Inject

class SearchFragment : Fragment(), View.OnClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
        initButtons()
        initFavoriteRecycler()
        listenViewModel()
    }

    private fun initFavoriteRecycler(){
        favoriteItems.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = FavoriteAdapter(object : OnDeleteItemCallback{
                override fun onItemDelete(cityEntity: CityEntity) {
                    viewModel.deleteCity(cityEntity)
                }
            })
        }
    }

    private fun updateListWithData(list: List<CityEntity>){
        (favoriteItems.adapter as FavoriteAdapter).setData(list)
    }

    private fun listenViewModel(){
        viewModel.cityLiveData.observe(viewLifecycleOwner, Observer {
            updateListWithData(it)
        })
    }

    private fun initButtons(){
        backButton.setOnClickListener(this)
        searchButton.setOnClickListener(this)
        addToFavButton.setOnClickListener (this)
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.backButton -> findNavController().navigateUp()
            R.id.searchButton ->  findNavController().navigate(R.id.action_searchFragment_to_weatherForecastFragment)
            R.id.addToFavButton -> viewModel.addCityToFavorite(cityNameTv.text.toString(), activity as Activity)
        }
    }
}
package com.popda.weatherforcast.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
class NetworkUtil(private val context: Context) {

    fun hasNetwork(): Boolean {
        var isConnected = false // Initial Value
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback(){
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    isConnected = true
                }
            })
        return isConnected
    }
}
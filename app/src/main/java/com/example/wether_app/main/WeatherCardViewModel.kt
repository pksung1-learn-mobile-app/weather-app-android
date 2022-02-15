package com.example.wether_app.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wether_app.model.Weather

class WeatherCardViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val position = MutableLiveData<Int>()
    val weather = MutableLiveData<Weather>()
}
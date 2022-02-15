package com.example.wether_app.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.wether_app.R
import com.example.wether_app.databinding.WeatherCardFragmentBinding
import com.example.wether_app.model.Weather
import com.example.wether_app.started.BottomCardFragment

class WeatherCardFragment : Fragment() {

    companion object {
        fun newInstance(position: Int, weather: Weather): WeatherCardFragment {
            val fragment: WeatherCardFragment = WeatherCardFragment().apply {
                arguments = Bundle().apply {
                    putInt("position", position)
                    putParcelable("weather", weather)
                }
            }
            return fragment
        }
    }

    private lateinit var viewModel: WeatherCardViewModel
    private lateinit var binding: WeatherCardFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(WeatherCardViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.weather_card_fragment, container, false)

        viewModel.weather.observe(this, updateWeather)
        viewModel.weather.value = receiveWeather
        return binding.root
    }

    private val receiveWeather by lazy { requireArguments().getParcelable<Weather>("weather") }

    private val updateWeather by lazy {
        Observer <Weather>{ weather ->
            binding.weather = weather
        }
    }

}
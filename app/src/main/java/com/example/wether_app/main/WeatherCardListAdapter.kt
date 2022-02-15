package com.example.wether_app.main

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wether_app.R
import com.example.wether_app.databinding.WeatherCardFragmentBinding
import com.example.wether_app.model.Weather

class WeatherCardListAdapter(private val dataset: Array<Weather>):
    RecyclerView.Adapter<WeatherCardListAdapter.ViewHolder>() {
    lateinit var binding: WeatherCardFragmentBinding



    class ViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.weather_card_fragment, parent, false)

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        binding.weather = dataset[position]
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}

class WeatherCardListItemDecorator(): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.set(100, 100, 100, 100)
        super.getItemOffsets(outRect, view, parent, state)
    }
}
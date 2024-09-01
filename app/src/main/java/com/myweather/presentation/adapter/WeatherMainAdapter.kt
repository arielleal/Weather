package com.myweather.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.myweather.R
import com.myweather.core.toTemperature
import com.myweather.databinding.WeatherItemListBinding
import com.myweather.domain.model.Forecast

class WeatherMainAdapter : ListAdapter<Forecast, WeatherMainAdapter.WeatherItemViewHolder>(DiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherItemViewHolder {
        return WeatherItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: WeatherItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class WeatherItemViewHolder(
        private val binding: WeatherItemListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): WeatherItemViewHolder {
                val binding: WeatherItemListBinding = WeatherItemListBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return WeatherItemViewHolder(binding)
            }
        }

        fun bind(item: Forecast) {
            binding.weatherImage.setImageResource(item.condition)
            binding.maxValue.text = item.maxTemp.toTemperature()
            binding.minValue.text = item.minTemp.toTemperature()
            binding.weekday.text = item.weekday
            binding.date.text = item.date
            binding.descriptionDay.text = item.description
        }
    }
}

private class DiffUtil : DiffUtil.ItemCallback<Forecast>() {
    override fun areItemsTheSame(oldItem: Forecast, newItem: Forecast): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Forecast, newItem: Forecast): Boolean {
        return oldItem == newItem
    }

}
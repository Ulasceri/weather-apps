package com.example.weatherapp.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.weatherapp.data.model.Weather

@BindingAdapter("weatherList")
fun setImage(imageView: ImageView, weatherList: List<Weather>?) {
    weatherList?.let {
        Glide.with(imageView).load("http://openweathermap.org/img/wn/${it[0].icon}@2x.png")
            .into(imageView)
    }
}
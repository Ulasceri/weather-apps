package com.example.weatherapp.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mainViewModel: MainViewModel

    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        sharedPref = getSharedPreferences(packageName, MODE_PRIVATE)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        with(binding) {

            mainActivity = this@MainActivity

            pbLoadingVisibleState = false
            tvErrorVisibleState = false

            cityName = getSavedCity()
            mainViewModel.getDataFromAPI(getSavedCity())

            swipeRefreshLayout.setOnRefreshListener {
                dataViewVisibleState = false
                pbLoadingVisibleState = false
                tvErrorVisibleState = false

                cityName = getSavedCity()
                mainViewModel.getDataFromAPI(getSavedCity())
                swipeRefreshLayout.isRefreshing = false
            }
        }

        getLiveData()
    }

    fun searchCityNameClick(cityName: String) {
        sharedPref.edit().putString("cityName", cityName).apply()
        mainViewModel.getDataFromAPI(cityName)
    }

    private fun getSavedCity() = sharedPref.getString("cityName", "ankara") ?: "ankara"

    private fun getLiveData() {

        with(binding) {
            with(mainViewModel) {
                weatherData.observe(this@MainActivity) { weatherData ->
                    data = weatherData
                    dataViewVisibleState = weatherData.main.temp.toString().isNotBlank()
                }

                loadingState.observe(this@MainActivity) {
                    pbLoadingVisibleState = it
                }

                errorState.observe(this@MainActivity) { error ->
                    tvErrorVisibleState = error
                }
            }
        }
    }
}
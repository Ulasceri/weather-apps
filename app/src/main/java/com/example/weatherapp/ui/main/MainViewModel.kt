package com.example.weatherapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.data.source.remote.WeatherAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {

    private val weatherAPIService = WeatherAPIService()
    private val disposable = CompositeDisposable()

    val weatherData = MutableLiveData<WeatherModel>()
    val errorState = MutableLiveData<Boolean>()
    val loadingState = MutableLiveData<Boolean>()

    fun getDataFromAPI(cityName: String) {
        loadingState.value = true
        disposable.add(
            weatherAPIService.getDataService(cityName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<WeatherModel>() {
                    override fun onSuccess(t: WeatherModel) {
                        weatherData.value = t
                        errorState.value = false
                        loadingState.value = false
                    }

                    override fun onError(e: Throwable) {
                        errorState.value = true
                        loadingState.value = false
                    }
                })
        )
    }
}
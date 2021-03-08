package com.bulich.misha.pogodakaliningrad2



import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bulich.misha.pogodakaliningrad2.api.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
private const val TAG = "SearchFragmentViewModel"
class SearchFragmentViewModel : ViewModel() {

    private val api =  MyApi.retrofitService
    private val disposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    private var mainTempMutableLiveData: MutableLiveData<WeatherParceble> = MutableLiveData()
    val mainTempLiveData: LiveData<WeatherParceble>
        get() = mainTempMutableLiveData

    fun getWeatherApi(cityName: String){
    mainTempMutableLiveData.value = null
        disposable.add(api.getWeather(cityName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val main = it.main.temp
                val name: String = it.name
                val pressure: Int = (it.main.pressure * 0.75).toInt()
                val humidity: Int = it.main.humidity
                val speed: Double = it.wind.speed
                val weather = WeatherParceble(main, name, pressure, humidity, speed)
                mainTempMutableLiveData.value = weather
                Log.d(TAG, "Получилось: ${weather}")

            }, {
                Log.d(TAG, "Ошибка", it)
            }))
    }
}
package com.bulich.misha.pogodakaliningrad2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import com.bulich.misha.pogodakaliningrad2.api.WeatherParceble

class MainActivity : AppCompatActivity(), SearchCityFragment.CallBacks {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment == null){
            val fragment = SearchCityFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }

    override fun onCitySearch(temp: WeatherParceble) {
        val fragment = ResultFragment.newInstance(temp)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
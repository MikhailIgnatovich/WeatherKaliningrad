package com.bulich.misha.pogodakaliningrad2

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bulich.misha.pogodakaliningrad2.api.WeatherOpenResponce
import com.bulich.misha.pogodakaliningrad2.api.WeatherParceble

private const val TEMP = "temp"
class ResultFragment : Fragment() {

    private lateinit var tempTextView: TextView
    private lateinit var cityTextView: TextView
    private lateinit var pressureTextView: TextView
    private lateinit var humidityTextView: TextView
    private lateinit var windTextView: TextView

    private  lateinit var argsTemp: WeatherParceble

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            argsTemp = arguments?.getParcelable<WeatherParceble>(TEMP) as WeatherParceble
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_result, container, false)

        cityTextView = view.findViewById(R.id.city_textView)
        tempTextView = view.findViewById(R.id.temp_textView)
        pressureTextView = view.findViewById(R.id.pressure_textView)
        humidityTextView = view.findViewById(R.id.humidity_textView)
        windTextView = view.findViewById(R.id.wind_textView)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cityTextView.text = argsTemp.name
        tempTextView.text = argsTemp.main.toString()
        pressureTextView.text = argsTemp.pressure.toString()
        humidityTextView.text = argsTemp.humidity.toString()
        windTextView.text = argsTemp.speed.toString()
    }

    companion object {
        fun newInstance(temp: WeatherParceble): ResultFragment {
            val args = Bundle().apply {
                putParcelable(TEMP, temp)
            }
            return ResultFragment().apply {
                arguments = args
            }
        }
    }
}
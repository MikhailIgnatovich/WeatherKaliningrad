package com.bulich.misha.pogodakaliningrad2

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bulich.misha.pogodakaliningrad2.api.WeatherParceble


private const val TAG = "SearchCityFragment"
class SearchCityFragment : Fragment() {

    interface CallBacks {
        fun onCitySearch(temp: WeatherParceble)
    }

    private var callBacks: CallBacks? = null

    private lateinit var cityEditText: EditText
    private lateinit var searchImageButton: ImageButton

    private val searchViewModel: SearchFragmentViewModel by lazy {
        ViewModelProvider(this).get(SearchFragmentViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBacks = context as CallBacks?
    }

    override fun onDetach() {
        super.onDetach()
        callBacks = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_search_city, container, false)

        cityEditText = view.findViewById(R.id.city_editText) as EditText
        searchImageButton = view.findViewById(R.id.search_ImageButton) as ImageButton

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            searchImageButton.setOnClickListener {
                val cityName = cityEditText.text.toString()
//                searchViewModel.cityNameMutableLiveData.value = cityName
                searchViewModel.getWeatherApi(cityName)
                    searchViewModel.mainTempLiveData.observe(viewLifecycleOwner, Observer {
                        it?.let {
                            Log.d(TAG, "Получаем2: ${it}, Название Города: $cityName")
                            callBacks?.onCitySearch(it)
                        }
                    })
            }
    }

    companion object {
        fun newInstance(): SearchCityFragment {
            return SearchCityFragment()
        }
    }
}
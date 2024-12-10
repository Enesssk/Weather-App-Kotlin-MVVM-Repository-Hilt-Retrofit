package com.example.weather_app_kotlin_mvvm_repository.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.weather_app_kotlin_mvvm_repository.R
import com.example.weather_app_kotlin_mvvm_repository.data.util.Resource
import com.example.weather_app_kotlin_mvvm_repository.data.util.Status
import com.example.weather_app_kotlin_mvvm_repository.databinding.FragmentMainBinding
import com.example.weather_app_kotlin_mvvm_repository.ui.viewmodel.WeatherViewModel


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel : WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(WeatherViewModel::class.java)


        binding.searchText.addTextChangedListener {
            it?.let {
                val cityName = binding.searchText.text.toString()
                viewModel.searchWeather(cityName)
                subscribeTheObservers()
            }
        }

        return binding.root
    }

    private fun subscribeTheObservers(){

        viewModel.weather.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    val cityName = binding.searchText.text.toString()

                    binding.constraint.visibility =  View.VISIBLE
                    binding.tempText.visibility =  View.VISIBLE
                    binding.cityName.visibility =  View.VISIBLE
                    binding.weatherIconImageView.visibility =  View.VISIBLE
                    binding.detailsText.visibility = View.VISIBLE
                    binding.weatherIconImageView.visibility = View.VISIBLE

                    binding.tempText.text = "${it?.data?.main?.temp?.toDouble()} C°"
                    binding.humudityText.text = "% ${it?.data?.main?.humidity?.toInt()}"
                    binding.seaLevelText.text = "${it?.data?.main?.seaLevel?.toInt()}"
                    binding.feelsTempText.text = "${it?.data?.main?.feelsLike?.toDouble()} C°"
                    binding.sunSetText.text = "${it?.data?.sys?.sunset?.toInt()}"
                    binding.sunRiseText.text = "${it?.data?.sys?.sunrise?.toInt()}"
                    binding.windSpeedText.text = "${it?.data?.wind?.speed?.toDouble()}"
                    binding.cityName.text =  "${cityName}"
                    it?.data?.weather?.get(0)?.icon?.let {url->
                        val iconUrl = "https://openweathermap.org/img/wn/${url}@2x.png"
                        Glide.with(requireContext()).load(iconUrl)
                            .override(300,300)
                            .into(binding.weatherIconImageView)
                    }
                    binding.detailsText.text = "${it?.data?.weather?.get(0)?.description}"


                }
                Status.ERROR ->{
                    binding.progressBar.visibility = View.GONE
                    Log.d("main","${it.message}")
                }
                Status.LOADING ->{
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })

    }


}
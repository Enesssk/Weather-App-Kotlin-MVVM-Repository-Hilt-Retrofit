package com.example.weather_app_kotlin_mvvm_repository.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.weather_app_kotlin_mvvm_repository.R
import com.example.weather_app_kotlin_mvvm_repository.databinding.FragmentEntranceBinding


class EntranceFragment : Fragment() {

    private lateinit var binding : FragmentEntranceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEntranceBinding.inflate(inflater,container,false)

        binding.button.setOnClickListener {
            Navigation.findNavController(it).navigate(EntranceFragmentDirections.actionEntranceFragmentToMainFragment())
        }

        return binding.root
    }


}
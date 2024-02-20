package com.example.pronedvizapp.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pronedvizapp.MainActivity
import com.example.pronedvizapp.R
import com.example.pronedvizapp.databinding.FragmentAboutActivityBinding
import com.example.pronedvizapp.model.Work

class AboutActivityFragment(val actualWork: Work) : Fragment() {

    lateinit var binding: FragmentAboutActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentAboutActivityBinding.inflate(inflater, container, false)

        binding.aboutActivityNameTextView.text = actualWork.workName
        binding.aboutActivityDescTextView.text = R.string.analytics_desc.toString()

        binding.closeImageButton.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.popBackStack()
        }

        binding.startActivityButton.setOnClickListener {
            // TODO: здесь когда будут написаны соответствующие франменты для каждой работы будет заполняться объект (1 фрагмент - 1 тип работы)

            MainActivity.actualTasks.add(actualWork)

            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.popBackStack()
        }

        return binding.root
    }
}
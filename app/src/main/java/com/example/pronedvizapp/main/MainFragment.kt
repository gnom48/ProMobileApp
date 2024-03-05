package com.example.pronedvizapp.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pronedvizapp.EditProfileActivity
import com.example.pronedvizapp.MainActivity
import com.example.pronedvizapp.adapters.MainActualWorksAdapter
import com.example.pronedvizapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.personalDataConstraintLayout.setOnClickListener {
            val intent = Intent(this.requireContext(), EditProfileActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

}
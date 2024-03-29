package com.example.pronedvizapp.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.pronedvizapp.adapters.MainInfoAdapter
import com.example.pronedvizapp.databinding.FragmentProfileBinding
import com.example.pronedvizapp.model.MainInfoForCard

class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.mainInfoRecyclerView.layoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.mainInfoRecyclerView)
        binding.mainInfoRecyclerView.adapter = MainInfoAdapter(arrayListOf(MainInfoForCard("16", "Звонков", "Нужно сделать"), MainInfoForCard("17", "Звонков", "Нужно сделать"), MainInfoForCard("18", "Звонков", "Нужно сделать")))

        return binding.root
    }
}
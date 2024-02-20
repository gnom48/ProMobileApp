package com.example.pronedvizapp.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.pronedvizapp.databinding.FragmentWorkBinding
import com.example.pronedvizapp.main.AboutActivityFragment
import com.example.pronedvizapp.model.Analytics
import com.example.pronedvizapp.model.Work

class WorkFragment : Fragment() {

    lateinit var binding: FragmentWorkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentWorkBinding.inflate(inflater, container, false)

        binding.analiticsButton.setOnClickListener{
            var analytics: Analytics = Analytics()

            showCommitFragment(analytics)

        }
        binding.searchButton.setOnClickListener{
            showCommitFragment(null!!)

        }
        binding.callsButton.setOnClickListener{
            showCommitFragment(null!!)

        }
        binding.advertsButton.setOnClickListener{
            showCommitFragment(null!!)

        }
        binding.showButton.setOnClickListener{
            showCommitFragment(null!!)

        }
        binding.meetButton.setOnClickListener{
            showCommitFragment(null!!)

        }
        binding.dealButton.setOnClickListener{
            showCommitFragment(null!!)

        }
        binding.dealButton.setOnClickListener{
            showCommitFragment(null!!)

        }

        return binding.root
    }

    private fun showCommitFragment(actualWork: Work) {
        val aboutActivityFragment = AboutActivityFragment(actualWork)

        val fragmentManager = (requireContext() as AppCompatActivity).supportFragmentManager
        fragmentManager.beginTransaction()
            .add(android.R.id.content, aboutActivityFragment)
            .addToBackStack(null)
            .commit()
    }
}
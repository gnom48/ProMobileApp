package com.example.pronedvizapp.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pronedvizapp.databinding.FragmentMainBinding
import com.example.pronedvizapp.databinding.FragmentNotesBinding

class NotesFragment : Fragment() {

    lateinit var binding: FragmentNotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentNotesBinding.inflate(inflater, container, false)

        return binding.root    }
}
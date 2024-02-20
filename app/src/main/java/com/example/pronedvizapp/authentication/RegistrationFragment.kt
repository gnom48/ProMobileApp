package com.example.pronedvizapp.authentication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pronedvizapp.MainActivity
import com.example.pronedvizapp.R
import com.example.pronedvizapp.databinding.FragmentAuthorizationBinding
import com.example.pronedvizapp.databinding.FragmentRegistrationBinding

class RegistrationFragment: Fragment() {

    lateinit var binding: FragmentRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        binding.completeButton.setOnClickListener {
            // TODO проверки
            // TODO подвязать SharedPreferences
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        binding.goBackImageButton.setOnClickListener {
            AuthenticationActivity.Companion.openAuthorization()
        }

        binding.goToAuthorizationTextView.setOnClickListener {
            AuthenticationActivity.Companion.openAuthorization()
        }

        return binding.root
    }
}
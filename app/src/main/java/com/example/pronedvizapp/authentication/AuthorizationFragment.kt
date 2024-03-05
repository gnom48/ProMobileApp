package com.example.pronedvizapp.authentication

import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.Animatable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.pronedvizapp.MainActivity
import com.example.pronedvizapp.R
import com.example.pronedvizapp.databinding.FragmentAuthorizationBinding

class AuthorizationFragment: Fragment() {

    lateinit var binding: FragmentAuthorizationBinding

    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAuthorizationBinding.inflate(inflater, container, false)
        preferences = this.requireContext().getSharedPreferences("settings", Context.MODE_PRIVATE)

        binding.gradientView.animateGradientColors()

        binding.goToRegistrationTextView.setOnClickListener {
            AuthenticationActivity.Companion.openRegistration()
        }

        if (preferences.contains("LAST_LOGIN")) {
            //enterLogin.setText(preferences.getString("LAST_LOGIN", ""))
        }
        if (preferences.contains("LAST_PASSWORD")) {
            //enterPassword.setText(preferences.getString("LAST_PASSWORD", ""))
        }

        binding.completeButton.setOnClickListener {
            // TODO проверки

            val editor = preferences.edit()
//            editor.putString("LAST_LOGIN", enterLogin.text.toString()).apply()
//            editor.putString("LAST_PASSWORD", enterPassword.text.toString()).apply()

            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        return binding.root
    }
}
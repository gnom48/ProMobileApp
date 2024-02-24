package com.example.pronedvizapp.authentication

import android.animation.ValueAnimator
import android.content.Intent
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAuthorizationBinding.inflate(inflater, container, false)

        binding.gradientView.animateGradientColors()

        binding.goToRegistrationTextView.setOnClickListener {
            AuthenticationActivity.Companion.openRegistration()
        }

        binding.completeButton.setOnClickListener {
            // TODO проверки
            // TODO подвязать SharedPreferences
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        return binding.root
    }
}
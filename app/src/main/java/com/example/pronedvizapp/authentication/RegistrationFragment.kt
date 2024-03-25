package com.example.pronedvizapp.authentication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.pronedvizapp.MainActivity
import com.example.pronedvizapp.databases.DbViewModel
import com.example.pronedvizapp.databases.models.UserOrm
import com.example.pronedvizapp.databinding.FragmentRegistrationBinding

class RegistrationFragment: Fragment() {

    lateinit var binding: FragmentRegistrationBinding

    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        preferences = this.requireContext().getSharedPreferences("settings", Context.MODE_PRIVATE)

        binding.gradientView.animateGradientColors()

        binding.completeButton.setOnClickListener {
            // TODO проверки

            var newUser = UserOrm(null,
                binding.enterNameEditText.text.toString(),
                "-",
                binding.enterSurnameEditText.text.toString(),
                "-",
                binding.enterUserLoginEditText.text.toString(),
                "-",
                binding.enterPasswordEditText.text.toString(),
                null,
                "")

            // TODO запрос на сервер (отправляем newUser на проверку)
            // если ответ нормальный то продолжаем

            MainActivity.currentUser = newUser

            var tViewModel = ViewModelProvider(this).get(DbViewModel::class.java)

            if (tViewModel.getUserByLogin(binding.enterUserLoginEditText.text.toString()) == null) {
                tViewModel.insertUser(newUser)
            } else {
                Toast.makeText(this@RegistrationFragment.requireContext(), "Ошибка записи в бд", Toast.LENGTH_SHORT).show()
            }

            val editor = preferences.edit()
            editor.putString("LAST_LOGIN", newUser.login).apply()
            editor.putString("LAST_PASSWORD", newUser.password).apply()

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
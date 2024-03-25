package com.example.pronedvizapp.authentication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.pronedvizapp.MainActivity
import com.example.pronedvizapp.databases.DbViewModel
import com.example.pronedvizapp.databases.models.UserOrm
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
            binding.enterLoginEditText.setText(preferences.getString("LAST_LOGIN", ""))
        }
        if (preferences.contains("LAST_PASSWORD")) {
            binding.enterPasswordEditText.setText(preferences.getString("LAST_PASSWORD", ""))
        }

        binding.completeButton.setOnClickListener {
            // TODO проверки

            // TODO запрос на сервер - должен вернуть полного User с id !!!
            val tmpUserFromServer: UserOrm = UserOrm(
                null,
                "Егор",
                "мужской",
                "Иванов",
                "Сергеевич",
                "gnom48",
                "89111758100",
                "1234",
                null,
                ""
            )

            if (!(tmpUserFromServer.login == binding.enterLoginEditText.text.toString() &&
                tmpUserFromServer.password == binding.enterPasswordEditText.text.toString())) {
                Toast.makeText(this@AuthorizationFragment.requireContext(), "Неверный логин или пароль", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var tViewModel = ViewModelProvider(this).get(DbViewModel::class.java)

            if (tViewModel.getUserByLogin(binding.enterLoginEditText.text.toString())?.value == null) {
                try {
                    tViewModel.insertUser(tmpUserFromServer)
                } catch (e: Exception) {
                    Toast.makeText(this@AuthorizationFragment.requireContext(), "Ошибка записи в бд", Toast.LENGTH_SHORT).show()
                }
            }
            MainActivity.currentUser = tmpUserFromServer

            val editor = preferences.edit()
            editor.putString("LAST_LOGIN", binding.enterLoginEditText.text.toString()).apply()
            editor.putString("LAST_PASSWORD", binding.enterPasswordEditText.text.toString()).apply()

            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        return binding.root
    }
}
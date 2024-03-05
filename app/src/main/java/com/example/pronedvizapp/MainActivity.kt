package com.example.pronedvizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.pronedvizapp.databinding.ActivityMainBinding
import com.example.pronedvizapp.main.MainFragment
import com.example.pronedvizapp.main.NotesFragment
import com.example.pronedvizapp.main.ProfileFragment
import com.example.pronedvizapp.main.WorkFragment
import com.example.pronedvizapp.model.User
import com.example.pronedvizapp.model.Work
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import java.util.Date

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // tmp
        currentUser = User("Егор Иванов", "1234", "Мужской", "+7(911)175-81-00", Date(2005, 1, 7))

        binding.gradientView.animateGradientColors()

        binding.bottomMenu.labelVisibilityMode = NavigationBarView.LABEL_VISIBILITY_LABELED

        val bottomMenu = findViewById<BottomNavigationView>(R.id.bottomMenu)
        bottomMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottomMenuItemWork -> {
                    loadFragment(WorkFragment())
                    true
                }

                R.id.bottomMenuItemMain -> {
                    loadFragment(ProfileFragment())
                    true
                }

                R.id.bottomMenuItemNotes -> {
                    loadFragment(NotesFragment())
                    true
                }

                R.id.bottomMenuItemProfile -> {
                    loadFragment(MainFragment())
                    true
                }

                else -> {
                    false
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mainContentFrame, fragment)
        transaction.commit()
    }

    companion object {
        lateinit var currentUser: User
        var actualTasks: ArrayList<Work> = ArrayList<Work>()
    }
}
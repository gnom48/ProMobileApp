package com.example.pronedvizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.pronedvizapp.databinding.ActivityMainBinding
import com.example.pronedvizapp.main.MainFragment
import com.example.pronedvizapp.main.NotesFragment
import com.example.pronedvizapp.main.WorkFragment
import com.example.pronedvizapp.model.User
import com.example.pronedvizapp.model.Work
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomMenu = findViewById<BottomNavigationView>(R.id.bottomMenu)
        bottomMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottomMenuItemWork -> {
                    loadFragment(WorkFragment())
                    true
                }

                R.id.bottomMenuItemMain -> {
                    loadFragment(MainFragment())
                    true
                }

                R.id.bottomMenuItemNotes -> {
                    loadFragment(NotesFragment())
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
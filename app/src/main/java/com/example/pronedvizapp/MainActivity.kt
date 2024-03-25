package com.example.pronedvizapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.pronedvizapp.databases.models.UserOrm
import com.example.pronedvizapp.databinding.ActivityMainBinding
import com.example.pronedvizapp.main.MainFragment
import com.example.pronedvizapp.main.NotesFragment
import com.example.pronedvizapp.main.ProfileFragment
import com.example.pronedvizapp.main.WorkFragment
import com.example.pronedvizapp.model.Work
import com.google.android.material.navigation.NavigationBarView

@Suppress("Since15")
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.gradientView.animateGradientColors()

        binding.bottomMenu.labelVisibilityMode = NavigationBarView.LABEL_VISIBILITY_LABELED

        binding.addNewNoteFloatingActionButton.setOnClickListener {
            loadFragment(CreateEditTaskFragment())
        }

        binding.bottomMenu.menu.getItem(0).isChecked = true
        binding.bottomMenu.setOnItemSelectedListener {
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

    override fun onDestroy() {
        super.onDestroy()
        currentUser = null
    }

    companion object {
        var currentUser: UserOrm? = null
        var actualTasks: ArrayList<Work> = ArrayList<Work>()
    }
}
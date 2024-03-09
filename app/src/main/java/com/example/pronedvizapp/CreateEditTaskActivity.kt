package com.example.pronedvizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pronedvizapp.databinding.ActivityCreateEditTaskBinding

class CreateEditTaskActivity : AppCompatActivity() {

    lateinit var binding: ActivityCreateEditTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateEditTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}
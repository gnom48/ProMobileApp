package com.example.pronedvizapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.pronedvizapp.databases.DbViewModel
import com.example.pronedvizapp.databases.LocalDb
import com.example.pronedvizapp.databases.models.NoteOrm
import com.example.pronedvizapp.databinding.FragmentCreateEditTaskBinding
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.Calendar
import java.util.Locale


class CreateEditTaskFragment : Fragment() {

    lateinit var selectedLocalDateTime: LocalDateTime
    lateinit var binding: FragmentCreateEditTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentCreateEditTaskBinding.inflate(inflater, container, false)

        selectedLocalDateTime = LocalDateTime.now()
        selectedLocalDateTime.plusHours(1)

        binding.setDateTextView.setOnClickListener {
            val calendar = Calendar.getInstance()

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this.requireContext(), DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = String.format(Locale.US, "%02d.%02d.%04d", selectedDay, selectedMonth + 1, selectedYear)
                selectedLocalDateTime = LocalDateTime.of(selectedYear, selectedMonth, selectedDay, selectedLocalDateTime.hour, selectedLocalDateTime.minute, 0)
                binding.setDateTextView.setText(selectedDate)
            }, year, month, day)

            datePickerDialog.show()
        }

        binding.setTimeTextView.setOnClickListener {
            val calendar = Calendar.getInstance()

            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(this.requireContext(), TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
                val selectedTime = String.format(Locale.US, "%02d:%02d", selectedHour, selectedMinute)
                selectedLocalDateTime = LocalDateTime.of(selectedLocalDateTime.year, selectedLocalDateTime.month, selectedLocalDateTime.dayOfMonth, selectedHour, selectedMinute, 0)
                binding.setTimeTextView.setText(selectedTime)
            }, hour, minute, true)

            timePickerDialog.show()
        }

        binding.completeButton.setOnClickListener {
            val dbContext = LocalDb.getDb(this.requireContext())
            val newNote = NoteOrm(2,
                binding.taskTitleEditText.text.toString(),
                binding.taskDescEditText.text.toString(),
                selectedLocalDateTime.toEpochSecond(ZoneOffset.UTC), 1
            )
            var tViewModel = ViewModelProvider(this).get(DbViewModel::class.java)
            tViewModel.insertNote(newNote)
        }

        return binding.root
    }
}
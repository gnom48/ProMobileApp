package com.example.pronedvizapp.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pronedvizapp.CreateEditTaskActivity
import com.example.pronedvizapp.adapters.DatesAdapter
import com.example.pronedvizapp.adapters.NotesAdapter
import com.example.pronedvizapp.adapters.OnDateItemClickListener
import com.example.pronedvizapp.databinding.FragmentNotesBinding
import com.example.pronedvizapp.model.Note
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

class NotesFragment : Fragment() {

    lateinit var binding: FragmentNotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getFormattedDateString(date: LocalDateTime): String {
        val zonedDateTime = date.atZone(ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy, EEEE", Locale("ru"))
        val formattedDateTime = zonedDateTime.format(formatter)
        return formattedDateTime
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentNotesBinding.inflate(inflater, container, false)

        var dateSource = arrayListOf(
            Note("Задача", "Описание задачи", LocalDateTime.of(2024, 3, 3, 12, 0, 0)),
            Note("Задача", "Описание задачи", LocalDateTime.of(2024, 3, 9, 12, 0, 0)),
            Note("Задача", "Описание задачи", LocalDateTime.of(2024, 3, 9, 12, 0, 0)),
            Note("Задача", "Описание задачи", LocalDateTime.of(2024, 3, 9, 12, 0, 0)),
            Note("Задача", "Описание задачи", LocalDateTime.of(2024, 3, 12, 12, 0, 0)))

        binding.notesRecyclerView.layoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL, false)
//        val snapHelper: SnapHelper = PagerSnapHelper()
//        snapHelper.attachToRecyclerView(binding.notesRecyclerView)
        binding.notesRecyclerView.adapter = NotesAdapter(ArrayList(dateSource.filter { it.noteTime.toLocalDate() == LocalDateTime.now().toLocalDate() }))

        binding.datesRecyclerView.layoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.datesRecyclerView.adapter = DatesAdapter(object : OnDateItemClickListener {
            override fun onItemClick(date: LocalDateTime) {
                binding.selectedDateTextView.setText(getFormattedDateString(date))
                binding.notesRecyclerView.adapter = NotesAdapter(ArrayList(dateSource.filter { it.noteTime.toLocalDate() == date.toLocalDate() }))
            }
        })

        binding.addNewTaskFloatingActionButton.setOnClickListener {
            val intent = Intent(this.requireContext(), CreateEditTaskActivity::class.java)
            this.requireContext().startActivity(intent)
        }

        binding.rootSwipeRefreshLayout.setOnRefreshListener {
            // TODO подтягивание обновлений из ...

            binding.rootSwipeRefreshLayout.isRefreshing = false
        }

        return binding.root
    }
}
package com.example.pronedvizapp.main

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pronedvizapp.adapters.DatesAdapter
import com.example.pronedvizapp.adapters.NotesAdapter
import com.example.pronedvizapp.adapters.OnDateItemClickListener
import com.example.pronedvizapp.databases.models.NoteOrm
import com.example.pronedvizapp.databinding.FragmentNotesBinding
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
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
            NoteOrm(1,"Задача", "Описание задачи", LocalDateTime.of(2024, 3, 3, 12, 0, 0).toEpochSecond(ZoneOffset.UTC), 1),
            NoteOrm(2,"Задача", "Описание задачи", LocalDateTime.of(2024, 3, 9, 12, 0, 0).toEpochSecond(ZoneOffset.UTC), 1),
            NoteOrm(3,"Задача", "Описание задачи", LocalDateTime.of(2024, 3, 9, 12, 0, 0).toEpochSecond(ZoneOffset.UTC), 1),
            NoteOrm(4,"Задача", "Описание задачи", LocalDateTime.of(2024, 3, 9, 12, 0, 0).toEpochSecond(ZoneOffset.UTC), 1),
            NoteOrm(5,"Задача", "Описание задачи", LocalDateTime.of(2024, 3, 12, 12, 0, 0).toEpochSecond(ZoneOffset.UTC), 1)
        )

        binding.notesRecyclerView.layoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL, false)
        var notesAdapter = NotesAdapter(ArrayList(dateSource.filter { LocalDateTime.ofInstant(Instant.ofEpochSecond(it.noteDateTime), ZoneOffset.UTC).toLocalDate() == LocalDateTime.now().toLocalDate() }))
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                notesAdapter.removeItem(viewHolder.adapterPosition)
            }
        })
        itemTouchHelper.attachToRecyclerView(binding.notesRecyclerView)
        binding.notesRecyclerView.adapter = notesAdapter

        binding.datesRecyclerView.layoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.datesRecyclerView.adapter = DatesAdapter(object : OnDateItemClickListener {
            override fun onItemClick(date: LocalDateTime) {
                binding.selectedDateTextView.setText(getFormattedDateString(date))
                binding.notesRecyclerView.adapter = NotesAdapter(ArrayList(dateSource.filter { LocalDateTime.ofInstant(Instant.ofEpochSecond(it.noteDateTime), ZoneOffset.UTC).toLocalDate() == date.toLocalDate() }))
            }
        })

        binding.rootSwipeRefreshLayout.setOnRefreshListener {
            // TODO подтягивание обновлений из ...

            binding.rootSwipeRefreshLayout.isRefreshing = false
        }

        return binding.root
    }
}
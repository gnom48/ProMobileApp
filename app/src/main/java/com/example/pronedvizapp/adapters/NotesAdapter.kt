package com.example.pronedvizapp.adapters

import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.pronedvizapp.R
import com.example.pronedvizapp.databinding.NoteCardBinding
import com.example.pronedvizapp.model.Note
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class NotesAdapter(val dataSource: ArrayList<Note>): RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val binding: NoteCardBinding = NoteCardBinding.bind(view)

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: Note) {
            if (ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), item.noteTime.toLocalDate()) < 0.toLong()) {
                binding.iconImageView.setImageResource(R.drawable.task_complete_icon)
                binding.card.setBackgroundResource(R.drawable.note_card_others_res)
                binding.indicatorPanel.setBackgroundResource(R.drawable.card_indicator_other_res)
            } else if (ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), item.noteTime.toLocalDate()) == 0.toLong()) {
                binding.iconImageView.setImageResource(R.drawable.task_progress_icon)
                binding.card.setBackgroundResource(R.drawable.notes_card_selected_res)
                binding.indicatorPanel.setBackgroundResource(R.drawable.card_indicator_selected_res)
            } else if (ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), item.noteTime.toLocalDate()) > 0.toLong()) {
                binding.iconImageView.setImageResource(R.drawable.task_planned_icon)
                binding.card.setBackgroundResource(R.drawable.note_card_others_res)
                binding.indicatorPanel.setBackgroundResource(R.drawable.card_indicator_other_res)
            }

            binding.titleTextView.setText(item.noteTitle)
            var tmpStr: String
            if (item.noteDesc.length > 100) {
                tmpStr = item.noteDesc.substring(0, 100) + "..."
            }
            tmpStr = item.noteDesc
            binding.contentTextView.setText(tmpStr)
            binding.timeTextView.setText(item.noteTime.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_card, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = dataSource.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(dataSource[position])
    }
}
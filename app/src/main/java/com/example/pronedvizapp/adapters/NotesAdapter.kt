package com.example.pronedvizapp.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.pronedvizapp.R
import com.example.pronedvizapp.databases.models.ActiveWorkOrm
import com.example.pronedvizapp.databases.models.INotesAdapterTemplete
import com.example.pronedvizapp.databases.models.NoteOrm
import com.example.pronedvizapp.databinding.NoteCardBinding
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit

class NotesAdapter(val dataSource: ArrayList<INotesAdapterTemplete>): RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(mItem: INotesAdapterTemplete) {
            val binding: NoteCardBinding = NoteCardBinding.bind(view)

            if (mItem is NoteOrm) {
                val item = mItem as NoteOrm

                val noteDatetime = LocalDateTime.ofInstant(Instant.ofEpochSecond(item.noteDateTime), ZoneOffset.UTC).toLocalDate()
                if (ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), noteDatetime) < 0.toLong()) {
                    binding.iconImageView.setImageResource(R.drawable.task_complete_icon)
                    binding.card.setBackgroundResource(R.drawable.note_card_others_res)
                    binding.indicatorPanel.setBackgroundResource(R.drawable.card_indicator_other_res)
                } else if (ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), noteDatetime) == 0.toLong()) {
                    binding.iconImageView.setImageResource(R.drawable.task_progress_icon)
                    binding.card.setBackgroundResource(R.drawable.notes_card_selected_res)
                    binding.indicatorPanel.setBackgroundResource(R.drawable.card_indicator_selected_res)
                } else if (ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), noteDatetime) > 0.toLong()) {
                    binding.iconImageView.setImageResource(R.drawable.task_planned_icon)
                    binding.card.setBackgroundResource(R.drawable.note_card_others_res)
                    binding.indicatorPanel.setBackgroundResource(R.drawable.card_indicator_other_res)
                }

                binding.titleTextView.setText(item.noteTitle)
                var tmpStr: String
                if (item.noteDescription.length > 100) {
                    tmpStr = item.noteDescription.substring(0, 100) + "..."
                }
                tmpStr = item.noteDescription
                binding.contentTextView.setText(tmpStr)
                binding.timeTextView.setText(
                    LocalDateTime.ofInstant(
                        Instant.ofEpochSecond(item.noteDateTime),
                        ZoneOffset.UTC
                    ).toLocalTime().toString()
                )
            } else if (mItem is ActiveWorkOrm) {
                val item = mItem as ActiveWorkOrm

                val workDatetime = LocalDateTime.ofInstant(Instant.ofEpochSecond(item.workStart + item.workDuration), ZoneOffset.UTC).toLocalDate()
                if (ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), workDatetime) < 0.toLong()) {
                    binding.card.setBackgroundResource(R.drawable.note_card_others_res)
                    binding.indicatorPanel.setBackgroundResource(R.drawable.card_indicator_other_res)
                } else if (ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), workDatetime) == 0.toLong()) {
                    binding.card.setBackgroundResource(R.drawable.notes_card_selected_res)
                    binding.indicatorPanel.setBackgroundResource(R.drawable.card_indicator_selected_res)
                } else if (ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), workDatetime) > 0.toLong()) {
                    binding.card.setBackgroundResource(R.drawable.note_card_others_res)
                    binding.indicatorPanel.setBackgroundResource(R.drawable.card_indicator_other_res)
                }

                //binding.iconImageView.setImageResource(R.drawable.task_progress_icon)
                binding.titleTextView.setText(item.workType)
                binding.contentTextView.setText("Работа")
                binding.timeTextView.setText(LocalDateTime.ofInstant(Instant.ofEpochSecond(item.workStart + item.workDuration), ZoneOffset.UTC).toLocalTime().toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_card, parent, false)
        return NoteViewHolder(view)
    }

    public fun removeItem(position: Int) {
        //        dataSource.removeAt(position)
        //        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int = dataSource.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(dataSource[position])
    }
}
package com.example.pronedvizapp.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.pronedvizapp.R
import com.example.pronedvizapp.databinding.DateCardBinding
import com.example.pronedvizapp.model.Note
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class DatesAdapter(private val listener: OnDateItemClickListener): RecyclerView.Adapter<DatesAdapter.DateViewHolder>() {

    @RequiresApi(Build.VERSION_CODES.O)
    var dataSource: ArrayList<LocalDateTime> = getDatesList()

    inner class DateViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val binding: DateCardBinding = DateCardBinding.bind(view)

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: LocalDateTime) {
            if (LocalDateTime.now().toLocalDate() == item.toLocalDate()) {
                binding.rootContainer.setBackgroundResource(R.drawable.notes_card_selected_res)
            } else {
                binding.rootContainer.setBackgroundResource(R.color.transparent0)
            }
            binding.numberTextView.text = "${item.dayOfMonth}"
            binding.dayOfWeekTextView.text = "${item.dayOfWeek.name.substring(0, 3)}"

//            binding.rootContainer.setOnClickListener {
//                if (LocalDateTime.now().toLocalDate() != item.toLocalDate()) {
//                    binding.rootContainer.setBackgroundResource(R.drawable.main_buttons_res)
//                }
//            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    public fun getDatesList(): ArrayList<LocalDateTime> {
        val now = LocalDateTime.now()

        val daysBeforeToday = ArrayList<LocalDateTime>()
        for (i in 10 downTo 1) {
            daysBeforeToday.add(now.minusDays(i.toLong()))
        }

        val daysAfterToday = ArrayList<LocalDateTime>()
        for (i in 1..10) {
            daysAfterToday.add(now.plusDays(i.toLong()))
        }

        val daysList = daysBeforeToday + now + daysAfterToday

        return  ArrayList(daysList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.date_card, parent, false)
        return DateViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getItemCount(): Int = dataSource.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        val item = dataSource[position]
        holder.bind(item)

        holder.itemView.setOnClickListener {
            listener.onItemClick(item)
        }
    }
}
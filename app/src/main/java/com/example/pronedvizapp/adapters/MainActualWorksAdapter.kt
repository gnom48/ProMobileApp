package com.example.pronedvizapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pronedvizapp.R
import com.example.pronedvizapp.databinding.ActualWorkCardBinding
import com.example.pronedvizapp.databinding.FragmentAboutActivityBinding
import com.example.pronedvizapp.model.Work

class MainActualWorksAdapter(val dataSource: ArrayList<Work>): RecyclerView.Adapter<MainActualWorksAdapter.WorkViewHolder>() {

    inner class WorkViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val binding: ActualWorkCardBinding = ActualWorkCardBinding.bind(view)

        fun bind(work: Work) {
            // TODO: все основные свойства, которые доступны из Work - карточка в общем виде
            binding.workNameTextView.text = work.workName
            binding.timerTextView.text = work.workDuration.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.actual_work_card, parent, false)
        return WorkViewHolder(view)
    }

    override fun getItemCount(): Int = dataSource.size

    override fun onBindViewHolder(holder: WorkViewHolder, position: Int) {
        holder.bind(dataSource[position])
    }
}
package com.example.pronedvizapp.main

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.pronedvizapp.MainActivity
import com.example.pronedvizapp.R
import com.example.pronedvizapp.databinding.FragmentAboutActivityBinding
import com.example.pronedvizapp.databinding.FragmentWorkBinding
import com.example.pronedvizapp.model.Analytics
import com.example.pronedvizapp.model.OtherWork
import com.example.pronedvizapp.model.Work
import java.time.Duration
import java.time.LocalTime
import java.util.Calendar
import java.util.Locale

class WorkFragment : Fragment() {

    lateinit var binding: FragmentWorkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentWorkBinding.inflate(inflater, container, false)

        binding.analyticsConstraintLayout.setOnClickListener{
            var analytics: Analytics = Analytics(this)
            showCommitAlertDialog(analytics, R.string.analytics_desc)

        }
        binding.searchConstraintLayout.setOnClickListener{
            var search: OtherWork = OtherWork(this, "Поиск")
            showCommitAlertDialog(search, R.string.search_desc)

        }
        binding.callsConstraintLayout.setOnClickListener{
            var calls: OtherWork = OtherWork(this, "Звонки")
            showCommitAlertDialog(calls, R.string.calls_desc)

        }
        binding.flyersConstraintLayout.setOnClickListener{
            var flyers: OtherWork = OtherWork(this, "Расклейка")
            showCommitAlertDialog(flyers, R.string.flyer_desc)

        }
        binding.showConstraintLayout.setOnClickListener{
            var show: OtherWork = OtherWork(this, "Показ")
            showCommitAlertDialog(show, R.string.show_desc)

        }
        binding.meetConstraintLayout.setOnClickListener{
            var meet: OtherWork = OtherWork(this, "Встреча")
            showCommitAlertDialog(meet, R.string.meet_desc)

        }
        binding.dealConstraintLayout.setOnClickListener{
            var deal: OtherWork = OtherWork(this, "Сделка")
            showCommitAlertDialog(deal, R.string.deal_desc)

        }
        binding.depositConstraintLayout.setOnClickListener{
            var deposit: OtherWork = OtherWork(this, "Задаток")
            showCommitAlertDialog(deposit, R.string.deposit_desc)

        }

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showCommitAlertDialog(actualWork: Work, desc: Int) {

        val binding = FragmentAboutActivityBinding.inflate(LayoutInflater.from(this.requireContext()))
        var selectedDuration: Duration = Duration.ofHours((LocalTime.now().hour + 1).toLong()).plusMinutes(LocalTime.now().minute.toLong())
        binding.setTimeTextView.setText(String.format(Locale.US, "%02d:%02d", selectedDuration.toHours(), selectedDuration.toMinutes()))

        binding.aboutActivityDescTextView.setText(getText(desc))
        binding.aboutActivityNameTextView.setText(actualWork.workName)

        val dialog = Dialog(this.requireContext())
        dialog.window?.setBackgroundDrawableResource(R.color.transparent0)
        dialog.setContentView(binding.root)
        dialog.show()

        binding.closeImageButton.setOnClickListener {
            dialog.dismiss()
        }

        binding.setTimeTextView.setOnClickListener {
            val calendar = Calendar.getInstance()

            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(this.requireContext(), TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
                val selectedTime = String.format(Locale.US, "%02d:%02d", selectedHour, selectedMinute)
                selectedDuration = Duration.ofHours((selectedHour + 1).toLong()).plusMinutes(selectedMinute.toLong())
                binding.setTimeTextView.setText(selectedTime)
            }, hour, minute, true)

            timePickerDialog.show()
        }

        binding.startActivityButton.setOnClickListener {
            actualWork.start(selectedDuration, binding.dontNotifyCheckBox.isChecked)
            MainActivity.actualTasks.add(actualWork)
            dialog.dismiss()
        }
    }
}
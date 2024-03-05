package com.example.pronedvizapp.main

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pronedvizapp.R
import com.example.pronedvizapp.databinding.FragmentAboutActivityBinding
import com.example.pronedvizapp.databinding.FragmentWorkBinding
import com.example.pronedvizapp.model.Analytics
import com.example.pronedvizapp.model.Work

class WorkFragment : Fragment() {

    lateinit var binding: FragmentWorkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentWorkBinding.inflate(inflater, container, false)

        binding.analyticsConstraintLayout.setOnClickListener{
            var analytics: Analytics = Analytics()

            showCommitAlertDialog(analytics)

        }
        binding.searchConstraintLayout.setOnClickListener{
            showCommitAlertDialog(null!!)

        }
        binding.callsConstraintLayout.setOnClickListener{
            showCommitAlertDialog(null!!)

        }
        binding.flyersConstraintLayout.setOnClickListener{
            showCommitAlertDialog(null!!)

        }
        binding.showConstraintLayout.setOnClickListener{
            showCommitAlertDialog(null!!)

        }
        binding.meetConstraintLayout.setOnClickListener{
            showCommitAlertDialog(null!!)

        }
        binding.dealConstraintLayout.setOnClickListener{
            showCommitAlertDialog(null!!)

        }
        binding.depositConstraintLayout.setOnClickListener{
            showCommitAlertDialog(null!!)

        }

        return binding.root
    }

    private fun showCommitAlertDialog(actualWork: Work) {

        val binding = FragmentAboutActivityBinding.inflate(LayoutInflater.from(this.requireContext()))

        val dialog = Dialog(this.requireContext())
        dialog.window?.setBackgroundDrawableResource(R.color.transparent0)
        dialog.setContentView(binding.root)
        dialog.show()

        binding.closeImageButton.setOnClickListener {
            dialog.dismiss()
        }

        binding.startActivityButton.setOnClickListener {
            // TODO запуск новой работы
        }
    }
}
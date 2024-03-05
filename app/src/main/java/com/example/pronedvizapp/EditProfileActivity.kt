package com.example.pronedvizapp

import android.app.Dialog
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.pronedvizapp.databinding.ActivityEditProfileBinding
import com.example.pronedvizapp.databinding.EditProfileGenderDialogBinding
import com.example.pronedvizapp.databinding.EditProfileNameDialogBinding
import com.example.pronedvizapp.databinding.EditProfilePhoneBinding
import com.example.pronedvizapp.databinding.FragmentAboutActivityBinding
import com.example.pronedvizapp.model.Work
import java.util.Locale

class EditProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nameToEditTextView.setText(MainActivity.currentUser.login)
        binding.userNameTextView.setText(MainActivity.currentUser.login)
        binding.phoneToEditTextView.setText(MainActivity.currentUser.phone)
        binding.genderToEditTextView.setText(MainActivity.currentUser.gender)

        binding.goBackImageButton.setOnClickListener {
            this@EditProfileActivity.finish()
        }

//        binding.editNameConstraintLayout.setOnClickListener {
//            val bindingDialog = EditProfileNameDialogBinding.inflate(LayoutInflater.from(this))
//
//            val dialog = Dialog(this)
//            dialog.window?.setBackgroundDrawableResource(R.color.transparent0)
//            dialog.setContentView(binding.root)
//            dialog.show()
//
//            bindingDialog.cancelButton.setOnClickListener {
//                dialog.dismiss()
//            }
//
//            bindingDialog.saveButton.setOnClickListener {
//                MainActivity.currentUser.login = bindingDialog.editText.text.toString()
//                dialog.dismiss()
//            }
//        }
//
//        binding.editPhoneConstraintLayout.setOnClickListener {
//            val bindingDialog = EditProfilePhoneBinding.inflate(LayoutInflater.from(this))
//
//            val dialog = Dialog(this)
//            dialog.window?.setBackgroundDrawableResource(R.color.transparent0)
//            dialog.setContentView(binding.root)
//            dialog.show()
//
//            bindingDialog.cancelButton.setOnClickListener {
//                dialog.dismiss()
//            }
//
//            bindingDialog.saveButton.setOnClickListener {
//                MainActivity.currentUser.phone = bindingDialog.editText.text.toString()
//                dialog.dismiss()
//            }
//        }
//
//        binding.editPhoneConstraintLayout.setOnClickListener {
//
//        }
//
//        binding.editGenderConstraintLayout.setOnClickListener {
//            val bindingDialog = EditProfileGenderDialogBinding.inflate(LayoutInflater.from(this))
//
//            val dialog = Dialog(this)
//            dialog.window?.setBackgroundDrawableResource(R.color.transparent0)
//            dialog.setContentView(binding.root)
//            dialog.show()
//            if (MainActivity.currentUser.gender == "Мужской") {
//                bindingDialog.male.isChecked = true
//                bindingDialog.female.isChecked = false
//                bindingDialog.nothing.isChecked = false
//            } else if (MainActivity.currentUser.gender == "Женский") {
//                bindingDialog.female.isChecked = true
//                bindingDialog.male.isChecked = false
//                bindingDialog.nothing.isChecked = false
//            }
//            else {
//                bindingDialog.nothing.isChecked = true
//                bindingDialog.male.isChecked = false
//                bindingDialog.female.isChecked = false
//            }
//
//            bindingDialog.cancelButton.setOnClickListener {
//                dialog.dismiss()
//            }
//
//            bindingDialog.radioGroup.setOnCheckedChangeListener { radioGroup, checkId ->
//                when(checkId) {
//                    bindingDialog.male.id -> {
//                        MainActivity.currentUser.gender = bindingDialog.male.text.toString()
//                    }
//                    bindingDialog.female.id -> {
//                        MainActivity.currentUser.gender = bindingDialog.male.text.toString()
//                    }
//                    bindingDialog.nothing.id -> {
//                        MainActivity.currentUser.gender = bindingDialog.male.text.toString()
//                    }
//                }
//            }
//
//            bindingDialog.saveButton.setOnClickListener {
//                // = bindingDialog.editText.text.toString()
//                // TODO поменять пол
//                dialog.dismiss()
//            }
//        }
    }
}
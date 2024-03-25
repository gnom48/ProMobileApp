package com.example.pronedvizapp.model

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pronedvizapp.MainActivity
import com.example.pronedvizapp.databases.DbViewModel
import com.example.pronedvizapp.databases.LocalDb
import com.example.pronedvizapp.databases.models.ActiveWorkOrm
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneOffset

abstract class Work(
    val workName: String
)
{
    lateinit var workStartTime: LocalDateTime
    lateinit var workDuration: Duration
    var notificationId: String? = null

    fun type() = this::class

    override fun toString(): String = "Name = '$workName' | Duration = '$workDuration' | IsControlled = '$notificationId' | type = '${type()}'"

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveToDb(context: Fragment) {
        val dbContext = LocalDb.getDb(context.requireContext())
        val newWork = ActiveWorkOrm(
            null,
            MainActivity.currentUser?.userId!!,
            workName,
            workStartTime.toEpochSecond(ZoneOffset.UTC),
            workDuration.seconds,
            notificationId
        )
        var tViewModel = ViewModelProvider(context).get(DbViewModel::class.java)
        try {
            tViewModel.insertActiveWork(newWork)
        } catch (e: Exception) {
            Toast.makeText(context.requireContext(), "Ошибка записи в бд", Toast.LENGTH_SHORT).show()
        }
    }

    abstract fun start(_workDuration: Duration, _isControlled: Boolean)
}

class Analytics(val context: Fragment, workName: String = "Аналитика") : Work(workName) {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun start(_workDuration: Duration, _isControlled: Boolean) {
        workStartTime = LocalDateTime.now()
        workDuration = _workDuration

        if (_isControlled) {
            // TODO запустить уведомление и забрать его id
        }

        saveToDb(context)
    }
}

class OtherWork(val context: Fragment, workName: String) : Work(workName) {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun start(_workDuration: Duration, _isControlled: Boolean) {
        workStartTime = LocalDateTime.now()
        workDuration = _workDuration

        if (_isControlled) {
            // TODO запустить уведомление и забрать его id
        }

        saveToDb(context)
    }
}

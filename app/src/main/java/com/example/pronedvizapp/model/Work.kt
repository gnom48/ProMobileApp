package com.example.pronedvizapp.model

import java.sql.Timestamp

abstract class Work(
    val workName: String
)
{
    lateinit var workDuration: Timestamp
    var isControlled: Boolean = false

    fun type() = this::class

    override fun toString(): String = "Name = '$workName' | Duration = '$workDuration' | IsControlled = '$isControlled' | type = '${type()}'"

    abstract fun init()
    fun start() {

    }

    fun finish() {

    }
}

class Analytics(workName: String = "Аналитика") : Work(workName) {

    final val workDescription = ""

    override fun init() {

    }
}
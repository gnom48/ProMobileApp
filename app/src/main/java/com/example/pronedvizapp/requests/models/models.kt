package com.example.pronedvizapp.requests.models

import java.time.LocalDateTime

enum class UserTypes(val description: String) {
    COMMERCIAL("Риелтер коммерческой недвижимости"),
    PRIVATE("Риелтер частной недвижимости")
}

enum class WorkTasksTypes(val description: String) {
    FLYERS("Рассклейка"),
    CALLS("Обзвон"),
    SHOW("Показ объекта"),
    MEET("Встреча по объекту"),
    DEAL("Сделка"),
    DEPOSIT("Получение задатка"),
    SEARCH("Поиск объектов"),
    ANALYTICS("Аналитика рынка")
}

data class User(
    val id: Int,
    val login: String,
    val password: String,
    val type: UserTypes
)

data class Note(
    val id: Int,
    val title: String,
    val desc: String?,
    val dateTime: LocalDateTime,
    val userId: Int
)

data class Task(
    val id: Int,
    val workType: WorkTasksTypes,
    val dateTime: LocalDateTime,
    val durationMinutes: Int,
    val userId: Int
)

data class Team(
    val id: Int,
    val name: String,
    val createdDateTime: LocalDateTime
)

enum class UserStatuses(val description: String) {
    OWNER("Владелец"),
    USER("Участник")
}

data class UserTeam(
    val teamId: Int,
    val userId: Int,
    val role: UserStatuses
)

data class Statistics(
    val userId: Int,
    val data: String
)

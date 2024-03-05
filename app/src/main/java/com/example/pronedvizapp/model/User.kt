package com.example.pronedvizapp.model

import java.util.Date

class User(
    var login: String,
    var password: String,
    var gender: String?,
    var phone: String?,
    var birthday: Date?
) {

}
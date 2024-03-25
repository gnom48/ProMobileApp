package com.example.pronedvizapp.requests

import com.example.pronedvizapp.requests.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ServerApiUsers {

    @POST("user/registration")
    fun registration(
        @Body user: User
    ): Call<Void>

    @GET("/user/authorization")
    fun authorization(
        @Query("login") login: String,
        @Query("password") password: String
    ): Call<String?>

}
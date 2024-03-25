package com.example.pronedvizapp.requests

import com.example.pronedvizapp.requests.models.Note
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ServerApiNotes {

    @GET("/all")
    fun getAllNotesByUserId(
        @Header("token_authorization") tokenAuthorization: String
    ): Call<List<Note>>

    @POST("/add")
    fun addNote(
        @Body note: Note,
        @Header("token_authorization") tokenAuthorization: String
    ): Call<Void>

    @DELETE("/delete")
    fun deleteNote(
        @Query("note_id") noteId: Int,
        @Header("token_authorization") tokenAuthorization: String
    ): Call<Void>

    @PUT("/edit")
    fun editNote(
        @Body note: Note,
        @Header("token_authorization") tokenAuthorization: String
    ): Call<Void>

}
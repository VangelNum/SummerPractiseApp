package com.example.summerpractiseapp.feature_contacts.data.api

import com.example.summerpractiseapp.feature_contacts.data.model.UserData
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("/users")
    suspend fun getAllUsers(): List<UserData>

    @DELETE("/delete_user")
    suspend fun deleteUser(@Query("phone") phone: String)
}
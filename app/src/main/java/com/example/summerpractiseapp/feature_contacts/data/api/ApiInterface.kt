package com.example.summerpractiseapp.feature_contacts.data.api

import com.example.summerpractiseapp.feature_contacts.data.model.UserData
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("/users")
    suspend fun getAllUsers(): List<UserData>
}
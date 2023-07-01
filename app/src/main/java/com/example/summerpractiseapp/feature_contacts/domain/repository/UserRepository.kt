package com.example.summerpractiseapp.feature_contacts.domain.repository

import com.example.summerpractiseapp.common.Resource
import com.example.summerpractiseapp.feature_contacts.data.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getAllUsers(): Flow<Resource<List<UserData>>>
    suspend fun deleteUser(phone: String)
}
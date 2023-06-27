package com.example.summerpractiseapp.feature_contacts.data.repository

import com.example.summerpractiseapp.common.Resource
import com.example.summerpractiseapp.feature_contacts.data.api.ApiInterface
import com.example.summerpractiseapp.feature_contacts.data.model.UserData
import com.example.summerpractiseapp.feature_contacts.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface
) : UserRepository {
    override fun getAllUsers(): Flow<Resource<List<UserData>>> = flow {
        try {
            val response = apiInterface.getAllUsers()
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error"))
        }
    }
}

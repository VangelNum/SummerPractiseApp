package com.example.summerpractiseapp.feature_recent_calls.domain.repository

import com.example.summerpractiseapp.common.Resource
import com.example.summerpractiseapp.feature_recent_calls.data.model.RecentCallsEntity
import kotlinx.coroutines.flow.Flow

interface RecentCallsRepository {
    fun getAllRecentCalls(): Flow<Resource<List<RecentCallsEntity>>>
    suspend fun deleteRecentCall(recentCall: RecentCallsEntity)
    suspend fun addRecentCall(recentCall: RecentCallsEntity)
    suspend fun getRecentCall(phone: String): RecentCallsEntity?
    suspend fun deleteRecentCallByPhone(phone: String)
}
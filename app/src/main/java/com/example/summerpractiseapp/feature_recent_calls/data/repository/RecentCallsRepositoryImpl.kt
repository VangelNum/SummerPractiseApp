package com.example.summerpractiseapp.feature_recent_calls.data.repository

import com.example.summerpractiseapp.common.Resource
import com.example.summerpractiseapp.feature_recent_calls.data.data_source.RecentCallsDao
import com.example.summerpractiseapp.feature_recent_calls.data.model.RecentCallsEntity
import com.example.summerpractiseapp.feature_recent_calls.domain.repository.RecentCallsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecentCallsRepositoryImpl @Inject constructor(
    private val dao: RecentCallsDao
) : RecentCallsRepository {
    override fun getAllRecentCalls(): Flow<Resource<List<RecentCallsEntity>>> = flow {
        try {
            val response = dao.getAllLatestPhone()
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error"))
        }
    }

    override suspend fun deleteRecentCall(recentCall: RecentCallsEntity) {
        dao.deleteFromLatest(recentCall)
    }

    override suspend fun addRecentCall(recentCall: RecentCallsEntity) {
        dao.addToLatest(recentCall)
    }
}
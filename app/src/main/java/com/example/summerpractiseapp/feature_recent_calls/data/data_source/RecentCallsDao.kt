package com.example.summerpractiseapp.feature_recent_calls.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.summerpractiseapp.feature_recent_calls.data.model.RecentCallsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentCallsDao {
    @Query("SELECT * FROM recentCallsTable")
    fun getAllLatestPhone(): Flow<List<RecentCallsEntity>>

    @Insert
    suspend fun addToLatest(contact: RecentCallsEntity)

    @Delete
    suspend fun deleteFromLatest(contact: RecentCallsEntity)

    @Query("SELECT * FROM recentCallsTable WHERE phone = :phone")
    suspend fun getRecentCall(phone: String): RecentCallsEntity?

    @Query("DELETE FROM recentCallsTable WHERE phone = :phone")
    suspend fun deleteFromLatestByPhone(phone: String)
}
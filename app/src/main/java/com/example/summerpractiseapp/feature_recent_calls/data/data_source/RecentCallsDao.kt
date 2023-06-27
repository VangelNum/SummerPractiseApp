package com.example.summerpractiseapp.feature_recent_calls.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.summerpractiseapp.feature_favourite.data.model.FavouriteContactsEntity
import com.example.summerpractiseapp.feature_recent_calls.data.model.RecentCallsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentCallsDao {
    @Query("SELECT * FROM recentCallsTable")
    suspend fun getAllLatestPhone(): List<RecentCallsEntity>

    @Insert
    suspend fun addToLatest(contact: RecentCallsEntity)

    @Delete
    suspend fun deleteFromLatest(contact: RecentCallsEntity)
}
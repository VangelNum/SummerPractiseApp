package com.example.summerpractiseapp.feature_recent_calls.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.summerpractiseapp.feature_recent_calls.data.model.RecentCallsEntity

@Database(entities = [RecentCallsEntity::class], version = 1)
abstract class RecentCallsDatabase: RoomDatabase() {
    abstract fun getDao(): RecentCallsDao
    companion object {
        const val RECENT_CALLS_DATABASE = "recent_calls_db"
    }
}
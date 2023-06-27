package com.example.summerpractiseapp.feature_recent_calls.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recentCallsTable")
data class RecentCallsEntity(
    @PrimaryKey(autoGenerate = false)
    val phone: String,
    val name: String,
    val file: String?,
    val fileName: String?,
    val fileExtension: String?
)
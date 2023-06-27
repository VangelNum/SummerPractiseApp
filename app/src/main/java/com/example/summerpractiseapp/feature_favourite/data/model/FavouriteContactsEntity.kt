package com.example.summerpractiseapp.feature_favourite.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favouriteContactsTable")
data class FavouriteContactsEntity(
    @PrimaryKey(autoGenerate = false)
    val phone: String,
    val name: String,
    val file: String?,
    val fileName: String?,
    val fileExtension: String?
)
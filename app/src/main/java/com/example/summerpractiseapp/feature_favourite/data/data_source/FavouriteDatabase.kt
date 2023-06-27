package com.example.summerpractiseapp.feature_favourite.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.summerpractiseapp.feature_favourite.data.model.FavouriteContactsEntity

@Database(entities = [FavouriteContactsEntity::class], version = 1)
abstract class FavouriteDatabase: RoomDatabase() {
    abstract fun getDao(): FavouriteContactsDao
    companion object {
        const val FAVOURITE_DATABASE = "favourite_database"
    }
}
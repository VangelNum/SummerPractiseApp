package com.example.summerpractiseapp.feature_favourite.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.summerpractiseapp.feature_favourite.data.model.FavouriteContactsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteContactsDao {
    @Query("SELECT * FROM favouriteContactsTable")
    fun getAllFavouriteContacts(): Flow<List<FavouriteContactsEntity>>

    @Insert
    suspend fun addContact(contact: FavouriteContactsEntity)

    @Delete
    suspend fun deleteContact(contact: FavouriteContactsEntity)

    @Query("DELETE FROM favouriteContactsTable WHERE phone= :phone")
    suspend fun deleteContactByPhone(phone: String)

}
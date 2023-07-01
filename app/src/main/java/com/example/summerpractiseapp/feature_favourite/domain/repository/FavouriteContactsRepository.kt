package com.example.summerpractiseapp.feature_favourite.domain.repository

import com.example.summerpractiseapp.common.Resource
import com.example.summerpractiseapp.feature_favourite.data.model.FavouriteContactsEntity
import kotlinx.coroutines.flow.Flow

interface FavouriteContactsRepository {
    fun getAllFavouriteContacts(): Flow<Resource<List<FavouriteContactsEntity>>>
    suspend fun insertFavouriteContact(contact: FavouriteContactsEntity)
    suspend fun deleteFavouriteContact(contact: FavouriteContactsEntity)
    suspend fun deleteFavouriteContactByPhone(phone: String)
}
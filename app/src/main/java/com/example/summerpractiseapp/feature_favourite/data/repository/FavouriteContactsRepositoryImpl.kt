package com.example.summerpractiseapp.feature_favourite.data.repository

import com.example.summerpractiseapp.common.Resource
import com.example.summerpractiseapp.feature_favourite.data.data_source.FavouriteContactsDao
import com.example.summerpractiseapp.feature_favourite.data.model.FavouriteContactsEntity
import com.example.summerpractiseapp.feature_favourite.domain.repository.FavouriteContactsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavouriteContactsRepositoryImpl @Inject constructor(
    private val dao: FavouriteContactsDao
) : FavouriteContactsRepository {
    override fun getAllFavouriteContacts(): Flow<Resource<List<FavouriteContactsEntity>>> = flow {
        emit(Resource.Loading)
        try {
            val response = dao.getAllFavouriteContacts().collect { listOfContacts->
                emit(Resource.Success(listOfContacts))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    override suspend fun insertFavouriteContact(contact: FavouriteContactsEntity) {
        dao.addContact(contact)
    }

    override suspend fun deleteFavouriteContact(contact: FavouriteContactsEntity) {
        dao.deleteContact(contact)
    }

    override suspend fun deleteFavouriteContactByPhone(phone: String) {
        dao.deleteContactByPhone(phone)
    }
}
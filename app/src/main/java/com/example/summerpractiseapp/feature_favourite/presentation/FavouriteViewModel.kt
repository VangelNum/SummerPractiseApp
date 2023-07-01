package com.example.summerpractiseapp.feature_favourite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.summerpractiseapp.common.Resource
import com.example.summerpractiseapp.feature_favourite.data.model.FavouriteContactsEntity
import com.example.summerpractiseapp.feature_favourite.domain.repository.FavouriteContactsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val repository: FavouriteContactsRepository
): ViewModel() {
    private val _favouriteState = MutableStateFlow<Resource<List<FavouriteContactsEntity>>>(Resource.Loading)
    val favouriteState = _favouriteState.asStateFlow()

    init {
        getAllFavouriteContacts()
    }

    private fun getAllFavouriteContacts() {
        repository.getAllFavouriteContacts().onEach {
            _favouriteState.value = it
        }.launchIn(viewModelScope)
    }

    fun deleteFavouriteContact(contact: FavouriteContactsEntity) {
        viewModelScope.launch {
            repository.deleteFavouriteContact(contact = contact)
        }
    }

    fun insertFavouriteContact(contact: FavouriteContactsEntity) {
        viewModelScope.launch {
            repository.insertFavouriteContact(contact = contact)
        }
    }

    fun deleteFavouriteContactByPhone(phone: String) {
        viewModelScope.launch {
            repository.deleteFavouriteContactByPhone(phone)
        }
    }
}


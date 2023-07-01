package com.example.summerpractiseapp.feature_contacts.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.summerpractiseapp.common.Resource
import com.example.summerpractiseapp.feature_contacts.data.model.UserData
import com.example.summerpractiseapp.feature_contacts.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    private val _userResponse = MutableStateFlow<Resource<List<UserData>>>(Resource.Loading)
    val userResponse = _userResponse.asStateFlow()

    init {
        getAllUsers()
    }

    private fun getAllUsers() {
        val response = repository.getAllUsers().onEach {
            _userResponse.value = it
        }.launchIn(viewModelScope)
    }

    fun deleteUser(phone: String) {
        viewModelScope.launch {
            repository.deleteUser(phone)
            getAllUsers()
        }
    }
}

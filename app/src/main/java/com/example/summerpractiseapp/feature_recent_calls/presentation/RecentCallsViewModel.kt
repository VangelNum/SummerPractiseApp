package com.example.summerpractiseapp.feature_recent_calls.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.summerpractiseapp.common.Resource
import com.example.summerpractiseapp.feature_recent_calls.data.model.RecentCallsEntity
import com.example.summerpractiseapp.feature_recent_calls.domain.repository.RecentCallsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecentCallsViewModel @Inject constructor(
    private val repository: RecentCallsRepository
):  ViewModel() {
    private val _recentCallsState = MutableStateFlow<Resource<List<RecentCallsEntity>>>(Resource.Loading)
    val recentCallsState = _recentCallsState.asStateFlow()

    init {
        getRecentCalls()
    }

    private fun getRecentCalls() {
        repository.getAllRecentCalls().onEach {
            _recentCallsState.value = it
        }.launchIn(viewModelScope)
    }

    fun addToRecentCalls(recentCall: RecentCallsEntity) {
        viewModelScope.launch {
            val existingCall = repository.getRecentCall(recentCall.phone)
            if (existingCall == null) {
                repository.addRecentCall(recentCall)
            } else Unit
        }
    }

    fun deleteFromRecentCalls(recentCall: RecentCallsEntity) {
        viewModelScope.launch {
            repository.deleteRecentCall(recentCall)
        }
    }

    fun deleteFromRecentCallsByPhone(phone: String) {
        viewModelScope.launch {
            repository.deleteRecentCallByPhone(phone)
        }
    }
}
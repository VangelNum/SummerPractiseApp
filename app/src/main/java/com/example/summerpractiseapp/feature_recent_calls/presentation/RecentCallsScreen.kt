package com.example.summerpractiseapp.feature_recent_calls.presentation

import androidx.compose.runtime.Composable
import com.example.summerpractiseapp.common.Resource
import com.example.summerpractiseapp.feature_favourite.data.model.FavouriteContactsEntity
import com.example.summerpractiseapp.feature_recent_calls.data.model.RecentCallsEntity

@Composable
fun RecentCallsScreen(
    favouriteState: Resource<List<FavouriteContactsEntity>>,
    addToFavourite: (contact: FavouriteContactsEntity) -> Unit,
    deleteFromFavourite: (contact: FavouriteContactsEntity) -> Unit,
    recentState: Resource<List<RecentCallsEntity>>,
    deleteFromRecentCalls: (contact: RecentCallsEntity) -> Unit,
) {

}
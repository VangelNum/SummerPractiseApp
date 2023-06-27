package com.example.summerpractiseapp

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class Screens(val route: String, @StringRes val name: Int,@DrawableRes val icon: Int) {
    object MainScreen: Screens("main_screen",R.string.main_screen, R.drawable.outline_home_24)
    object FavouriteScreen: Screens("favourite_screen",R.string.favourite, R.drawable.outline_favorite_border_24)
    object RecentCallsScreen: Screens("recent_calls_screen",R.string.recent, R.drawable.outline_phone_24)
}
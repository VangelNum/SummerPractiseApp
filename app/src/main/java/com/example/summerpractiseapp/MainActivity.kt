package com.example.summerpractiseapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.summerpractiseapp.feature_contacts.presentation.MainScreen
import com.example.summerpractiseapp.feature_contacts.presentation.MainViewModel
import com.example.summerpractiseapp.feature_favourite.presentation.FavouriteScreen
import com.example.summerpractiseapp.feature_favourite.presentation.FavouriteViewModel
import com.example.summerpractiseapp.feature_recent_calls.presentation.RecentCallsScreen
import com.example.summerpractiseapp.feature_recent_calls.presentation.RecentCallsViewModel
import com.example.summerpractiseapp.ui.theme.SummerPractiseAppTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SummerPractiseAppTheme {
                val mainViewModel: MainViewModel by viewModels()
                val userState = mainViewModel.userResponse.collectAsStateWithLifecycle().value
                val favouriteViewModel: FavouriteViewModel by viewModels()
                val favouriteState =
                    favouriteViewModel.favouriteState.collectAsStateWithLifecycle().value
                val navController = rememberAnimatedNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                val items = listOf(
                    Screens.MainScreen,
                    Screens.FavouriteScreen,
                    Screens.RecentCallsScreen
                )
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        bottomBar = {
                            NavigationBar {
                                items.forEach { screen ->
                                    NavigationBarItem(
                                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                        onClick = {
                                            navController.navigate(screen.route) {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        },
                                        icon = {
                                            Icon(
                                                painterResource(id = screen.icon),
                                                contentDescription = null
                                            )
                                        },
                                        label = {
                                            Text(text = stringResource(id = screen.name))
                                        },
                                        alwaysShowLabel = true
                                    )
                                }
                            }
                        }
                    ) { innerPadding ->
                        AnimatedNavHost(
                            navController,
                            startDestination = Screens.MainScreen.route,
                            Modifier.padding(innerPadding)
                        ) {
                            composable(Screens.MainScreen.route) {
                                MainScreen(
                                    favouriteState,
                                    userState,
                                    addToFavourite = {
                                        favouriteViewModel.insertFavouriteContact(it)
                                    },
                                    deleteFromFavourite = {
                                        favouriteViewModel.deleteFavouriteContact(it)
                                    }
                                )
                            }
                            composable(Screens.FavouriteScreen.route) {
                                FavouriteScreen(
                                    favouriteState = favouriteState,
                                    deleteFromFavourite = {
                                        favouriteViewModel.deleteFavouriteContact(it)
                                    }
                                )
                            }
                            composable(Screens.RecentCallsScreen.route) {
                                val recentViewModel: RecentCallsViewModel = hiltViewModel()
                                val recentState = recentViewModel.recentCallsState.collectAsStateWithLifecycle().value
                                RecentCallsScreen(
                                    favouriteState = favouriteState,
                                    addToFavourite = {
                                        favouriteViewModel.insertFavouriteContact(it)
                                    },
                                    deleteFromFavourite = {
                                        favouriteViewModel.deleteFavouriteContact(it)
                                    },
                                    recentState = recentState,
                                    deleteFromRecentCalls = {
                                        recentViewModel.deleteFromRecentCalls(it)
                                    },
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SummerPractiseAppTheme {
        Greeting("Android")
    }
}
package dev.eastar.composewithviewmodeldemo.ui.main

import android.annotation.SuppressLint
import android.log.Log
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import dagger.hilt.android.AndroidEntryPoint
import dev.eastar.composewithviewmodeldemo.ui.theme.ComposeWithViewModelDemoTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel0: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(viewModel0)
        setContent {
            ComposeWithViewModelDemoTheme {
                val viewModel1: MainActivityViewModel = viewModel()
                Log.e(viewModel1)
                MainActivityApp()
            }
        }
    }
}

sealed class Route(val route: String) {
    object Home : Route("Home")
    object Favorite : Route("Favorite")
}


@Composable
private fun MainActivityApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Home, contentDescription = Route.Home.route) },
                    selected = currentDestination?.hierarchy?.any { it.route == Route.Home.route } == true,
                    onClick = {
                        navController.navigate(Route.Home.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Favorite, contentDescription = Route.Favorite.route) },
                    selected = currentDestination?.hierarchy?.any { it.route == Route.Favorite.route } == true,
                    onClick = {
                        navController.navigate(Route.Favorite.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            val viewModel2: MainActivityViewModel = hiltViewModel()
            Log.e(viewModel2)
            NavHost(
                navController = navController,
                startDestination = "parent",
            ) {
                // https://developer.android.com/jetpack/compose/libraries#hilt-navigation
                navigation(Route.Home.route, "parent") {
                    composable(Route.Home.route) {
                        val parentStackEntry = navController.getBackStackEntry("parent")
                        val parentViewModel: MainActivityViewModel = hiltViewModel(parentStackEntry)
                        Log.w(parentViewModel)
                        Greeting(Route.Home.route)
                    }
                    composable(Route.Favorite.route) {
                        val parentStackEntry = navController.getBackStackEntry("parent")
                        val parentViewModel: MainActivityViewModel = hiltViewModel(parentStackEntry)
                        Log.w(parentViewModel)
                        Greeting(Route.Favorite.route)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    val viewModel_X: MainActivityViewModel = hiltViewModel()
    Log.a(viewModel_X,viewModel_X.sayHello())
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeWithViewModelDemoTheme {
        MainActivityApp()
    }
}


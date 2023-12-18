package com.example.myjetpackfront.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screens(val route: String) {

    object ProfileScreens : Screens("profile_screen")
    object LoadingScreens : Screens("loading_screen")
    object ErrorScreens : Screens("error_screen")
    object StocksGridScreen: Screens("stocks_grid_screen")
    object ProfileScreen: Screens("profile_screen")
    object HomeScreens : Screens("home_screen")
}


object AppRouter {

    var currentScreens: MutableState<Screens> = mutableStateOf(Screens.HomeScreens)

    fun navigateTo(destination : Screens){
        currentScreens.value = destination
    }


}
package com.pru.composeroom.ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pru.composeroom.ui.presentation.home.HomeScreen
import com.pru.composeroom.ui.presentation.login.LoginScreen
import com.pru.composeroom.ui.presentation.registration.RegistrationScreen
import com.pru.composeroom.utils.ScreenRoute

@Composable
fun NavigationController() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.HomeScreen.routeName
    ) {
        composable(route = ScreenRoute.RegistrationScreen.routeName) {
            RegistrationScreen(navController = navController)
        }
        composable(route = ScreenRoute.LoginScreen.routeName) {
            LoginScreen(navController = navController)
        }
        composable(route = ScreenRoute.HomeScreen.routeName) {
            HomeScreen(navController = navController)
        }
    }
}
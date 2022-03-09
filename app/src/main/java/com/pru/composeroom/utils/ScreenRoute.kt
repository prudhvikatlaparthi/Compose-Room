package com.pru.composeroom.utils

sealed class ScreenRoute(val routeName: String) {
    object RegistrationScreen : ScreenRoute(routeName = "registration_screen")
    object LoginScreen : ScreenRoute(routeName = "login_screen")
    object HomeScreen : ScreenRoute(routeName = "home_screen")
    object PopBack : ScreenRoute(routeName = "pop_back")
    object None : ScreenRoute(routeName = "none")
}

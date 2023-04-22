package com.luc.login.ui.navigation

sealed class NavDestination(val route: String) {
    object MainScreen: NavDestination("mainScreen")
    object HomeScreen: NavDestination("homeScreen")
    object SignInScreen: NavDestination("signInScreen")
    object SignUpScreen: NavDestination("signUpScreen")
}

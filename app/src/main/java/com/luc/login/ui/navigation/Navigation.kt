/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.luc.login.ui.navigation

import com.luc.login.ui.MainScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.luc.login.domain.model.User
import com.luc.login.ui.HomeScreen
import com.luc.login.ui.SignInScreen
import com.luc.login.ui.SignUpScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    val actions = remember(navController) { MainActions(navController) }

    NavHost(navController = navController, startDestination = NavDestination.MainScreen.route) {
        composable(NavDestination.MainScreen.route) {
            MainScreen(
                navigateToSignIn = { actions.signInScreen(it) }
            )
        }

        composable(NavDestination.SignInScreen.route) {
            SignInScreen (
                navigateToSignUp = { actions.signUpScreen(it) },
                navigateToHome = { user -> actions.homeScreen(user, it) }
            )
        }

        composable(NavDestination.SignUpScreen.route) {
            SignUpScreen(
                navigateToSignIn = { navController.popBackStack() },
                navigateToHome = { user -> actions.homeScreen(user, it) }
            )
        }

        composable(
            "${NavDestination.HomeScreen.route}/{user}",
            arguments = listOf(
                navArgument("user") { type = NavType.StringType })
        ) { navBackStackEntry ->
            val arguments = requireNotNull(navBackStackEntry.arguments)
            val userJson = arguments.getString("user", "defaultValue")
            val user = Gson().fromJson<User>(userJson, object : TypeToken<User>() {}.type)
            HomeScreen(user)
        }

        // TODO: Add more destinations
    }
}

/**
 * Models the navigation actions in the app.
 */
class MainActions(navController: NavHostController) {
    val homeScreen: (User, NavBackStackEntry) -> Unit = { user, from ->
        val userJson = Gson().toJson(user)
        if (from.lifecycleIsResumed())
            navController.navigate("${NavDestination.HomeScreen.route}/$userJson")
    }

    val signInScreen: (NavBackStackEntry) -> Unit = {
        if (it.lifecycleIsResumed())
            navController.navigate(NavDestination.SignInScreen.route)
    }
    val signUpScreen: (NavBackStackEntry) -> Unit = {
        if (it.lifecycleIsResumed())
            navController.navigate(NavDestination.SignUpScreen.route)
    }
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.getLifecycle().currentState == Lifecycle.State.RESUMED

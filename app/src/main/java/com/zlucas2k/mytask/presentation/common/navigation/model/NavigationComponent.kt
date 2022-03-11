package com.zlucas2k.mytask.presentation.common.navigation.model

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zlucas2k.mytask.presentation.home.HomeScreen

@Composable
fun NavigationComponent(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screen.HomeScreen.route) {
        composable(Screen.HomeScreen.route) {
            HomeScreen()
        }

        composable(Screen.TaskScreen.route) {
            // TODO: Abrir TaskScreen
        }
    }
}
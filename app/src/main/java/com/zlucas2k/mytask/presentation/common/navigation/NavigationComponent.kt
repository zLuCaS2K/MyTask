package com.zlucas2k.mytask.presentation.common.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.zlucas2k.mytask.presentation.common.navigation.model.Screen
import com.zlucas2k.mytask.presentation.home.HomeScreen
import com.zlucas2k.mytask.presentation.task.TaskScreen

@Composable
@ExperimentalMaterialApi
fun NavigationComponent(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navHostController)
        }
        composable(
            route = Screen.TaskScreen.route + "?id={id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) {
            TaskScreen(navHostController)
        }
    }
}
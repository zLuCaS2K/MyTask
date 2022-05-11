package com.zlucas2k.mytask.presentation.common.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.zlucas2k.mytask.presentation.common.navigation.model.Screen
import com.zlucas2k.mytask.presentation.screens.home.HomeScreen
import com.zlucas2k.mytask.presentation.screens.task.add_task.AddTaskScreen
import com.zlucas2k.mytask.presentation.screens.task.edit_task.EditTaskScreen

@Composable
@ExperimentalMaterialApi
fun NavigationComponent(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navHostController = navHostController)
        }

        composable(route = Screen.AddTaskScreen.route) {
            AddTaskScreen(navController = navHostController)
        }

        composable(
            route = Screen.EditTaskScreen.route + "?id={id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) {
            EditTaskScreen(navController = navHostController)
        }
    }
}
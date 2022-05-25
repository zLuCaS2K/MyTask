package com.zlucas2k.mytask.presentation.common.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.zlucas2k.mytask.presentation.common.navigation.model.Screen
import com.zlucas2k.mytask.presentation.screens.home.HomeScreen
import com.zlucas2k.mytask.presentation.screens.task.add_task.AddTaskScreen
import com.zlucas2k.mytask.presentation.screens.task.edit_task.EditTaskScreen

@OptIn(ExperimentalComposeUiApi::class)
@Composable
@ExperimentalMaterialApi
@ExperimentalAnimationApi
fun NavigationComponent() {
    val navController = rememberAnimatedNavController()
    val springSpec = spring<IntOffset>(dampingRatio = Spring.DampingRatioMediumBouncy)

    AnimatedNavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navHostController = navController)
        }

        composable(
            route = Screen.AddTaskScreen.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = springSpec)
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = springSpec)
            },
        ) {
            AddTaskScreen(navController = navController)
        }

        composable(
            route = Screen.EditTaskScreen.route + "?id={id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            ),
            enterTransition = {
                fadeIn(animationSpec = tween(1000))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(1000))
            },
        ) {
            EditTaskScreen(navController = navController)
        }
    }
}
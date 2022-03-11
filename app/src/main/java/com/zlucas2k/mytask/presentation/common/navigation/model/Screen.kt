package com.zlucas2k.mytask.presentation.common.navigation.model

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object TaskScreen : Screen("task_screen")
}

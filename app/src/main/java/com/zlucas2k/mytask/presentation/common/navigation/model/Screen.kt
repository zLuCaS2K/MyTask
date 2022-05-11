package com.zlucas2k.mytask.presentation.common.navigation.model

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object AddTaskScreen : Screen("add_task_screen")
    object EditTaskScreen : Screen("edit_task_screen")
}

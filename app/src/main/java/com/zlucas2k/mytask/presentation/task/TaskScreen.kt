package com.zlucas2k.mytask.presentation.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.zlucas2k.mytask.presentation.task.viewmodel.TaskViewModel

@Composable
fun TaskScreen(navHostController: NavHostController, viewModel: TaskViewModel = viewModel()) {

    val state = viewModel.state.value

    Scaffold(
        topBar = {

        },
        content = {
            TaskScreenContent(
                title = state.title,
                onTitleChange = {
                    viewModel.onTitleChange(it)
                },
                date = state.date,
                onDateChange = {
                    viewModel.onDateChange(it)
                },
                time = state.time,
                onTimeChange = {
                    viewModel.onTimeChange(it)
                },
                description = state.description,
                onDescriptionChange = {
                    viewModel.onDescriptionChange(it)
                },
                priority = state.priority,
                onPrioritySelected = {
                    viewModel.onPriorityChange(it)
                },
                status = state.status,
                onStatusChange = {
                    viewModel.onStatusChange(it)
                }
            )
        }
    )
}
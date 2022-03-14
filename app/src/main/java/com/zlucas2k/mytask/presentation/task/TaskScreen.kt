package com.zlucas2k.mytask.presentation.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.zlucas2k.mytask.presentation.task.components.TaskTopAppBar
import com.zlucas2k.mytask.presentation.task.viewmodel.TaskViewModel

@Composable
fun TaskScreen(navHostController: NavHostController, viewModel: TaskViewModel = hiltViewModel()) {

    val state = viewModel.state.value
    val isEditing = state.selectedId == -1

    Scaffold(
        topBar = {
            TaskTopAppBar(
                isEditing = isEditing,
                onDeleteClick = { viewModel.onDeleteNote() },
                onSaveClick = { viewModel.onSaveNote() },
                onBackPressed = { navHostController.popBackStack() }
            )
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
package com.zlucas2k.mytask.presentation.task

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.zlucas2k.mytask.presentation.task.viewmodel.TaskViewModel

@Composable
fun TaskScreen(navHostController: NavHostController, viewModel: TaskViewModel = hiltViewModel()) {

    val state = viewModel.state.value
    val isEditing = state.selectedId == -1

    Scaffold(
        topBar = {
            TopAppBar(
                title = {

                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onPrimary
                        )
                    }
                },
                actions = {
                    if (isEditing) {
                        IconButton(onClick = { viewModel.onDeleteNote() }) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = null,
                                tint = MaterialTheme.colors.onPrimary
                            )
                        }
                    }
                    IconButton(onClick = { viewModel.onSaveNote() }) {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onPrimary
                        )
                    }
                },
                elevation = 0.dp
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
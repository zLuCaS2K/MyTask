package com.zlucas2k.mytask.presentation.task

import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.zlucas2k.mytask.presentation.task.common.TaskEventUI
import com.zlucas2k.mytask.presentation.task.components.TaskTopAppBar
import com.zlucas2k.mytask.presentation.task.viewmodel.TaskViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun TaskScreen(navHostController: NavHostController, viewModel: TaskViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val state = viewModel.state.value
    val isEditing = state.selectedId == -1

    LaunchedEffect(key1 = true) {
        viewModel.eventUI.collectLatest { taskEvent ->
            when (taskEvent) {
                is TaskEventUI.SaveTask -> {
                    Toast.makeText(context, "Salvo com Sucesso", Toast.LENGTH_SHORT).show()
                    navHostController.navigateUp()
                }
                is TaskEventUI.DeleteTask -> {
                    Toast.makeText(context, "Deletado com Sucesso", Toast.LENGTH_SHORT).show()
                    navHostController.navigateUp()
                }
                is TaskEventUI.ShowToast -> {
                    Toast.makeText(context, taskEvent.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

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
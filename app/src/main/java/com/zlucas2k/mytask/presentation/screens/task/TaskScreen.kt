package com.zlucas2k.mytask.presentation.screens.task

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.zlucas2k.mytask.R
import com.zlucas2k.mytask.presentation.components.MyTaskAlertDialog
import com.zlucas2k.mytask.presentation.screens.task.common.TaskEventUI
import com.zlucas2k.mytask.presentation.screens.task.components.form.TaskForm
import com.zlucas2k.mytask.presentation.screens.task.components.topbar.TaskTopAppBar
import kotlinx.coroutines.flow.collectLatest

@Composable
fun TaskScreen(navHostController: NavHostController, viewModel: TaskViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val uiState by viewModel.uiState
    val showDialogDeleteConfirmation = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        viewModel.uiEvent.collectLatest { uiEvent ->
            when (uiEvent) {
                is TaskEventUI.SaveTask -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.save_sucess_task),
                        Toast.LENGTH_SHORT
                    ).show()
                    navHostController.navigateUp()
                }
                is TaskEventUI.DeleteTask -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.delete_sucess_task),
                        Toast.LENGTH_SHORT
                    ).show()
                    navHostController.navigateUp()
                }
                is TaskEventUI.ShowToast -> {
                    Toast.makeText(context, uiEvent.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TaskTopAppBar(
                isEditing = uiState.isEditing,
                onDeleteClick = {
                    showDialogDeleteConfirmation.value = true
                },
                onSaveClick = { viewModel.onSaveNote() },
                onBackPressed = { navHostController.popBackStack() }
            )
        },
        content = {
            TaskForm(
                isEditing = uiState.isEditing,
                title = uiState.task.title,
                onTitleChange = viewModel::onTitleChange,
                time = uiState.task.time,
                onTimeChange = viewModel::onTimeChange,
                date = uiState.task.date,
                onDateChange = viewModel::onDateChange,
                description = uiState.task.description,
                onDescriptionChange = viewModel::onDescriptionChange,
                priority = uiState.task.priority,
                onPriorityChange = viewModel::onPriorityChange,
                status = uiState.task.status,
                onStatusChange = viewModel::onStatusChange,
                modifier = Modifier.fillMaxSize()
            )
        }
    )

    if (showDialogDeleteConfirmation.value) {
        MyTaskAlertDialog(
            title = stringResource(id = R.string.delete_task_title_dialog),
            textDescription = stringResource(id = R.string.delete_task_description_dialog),
            onConfirmClick = {
                viewModel.onDeleteNote()
                showDialogDeleteConfirmation.value = !showDialogDeleteConfirmation.value
            },
            onDismissClick = {
                showDialogDeleteConfirmation.value = !showDialogDeleteConfirmation.value
            }
        )
    }
}
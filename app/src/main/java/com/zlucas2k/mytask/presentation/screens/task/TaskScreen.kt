package com.zlucas2k.mytask.presentation.screens.task

import android.widget.Toast
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.zlucas2k.mytask.R
import com.zlucas2k.mytask.presentation.screens.task.common.TaskEventUI
import com.zlucas2k.mytask.presentation.screens.task.components.topbar.TaskTopAppBar
import kotlinx.coroutines.flow.collectLatest

@Composable
fun TaskScreen(navHostController: NavHostController, viewModel: TaskViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val uiState by viewModel.uiState
    val showDialogDeleteConfirmation = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        viewModel.uiEvent.collectLatest { uiEvent ->
            when(uiEvent) {
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
            TaskScreenForm(
                isEditing = uiState.isEditing,
                title = uiState.task.title,
                onTitleChange = {
                    viewModel.onTitleChange(it)
                },
                date = uiState.task.date,
                onDateChange = {
                    viewModel.onDateChange(it)
                },
                time = uiState.task.time,
                onTimeChange = { hour, minute ->
                    viewModel.onTimeChange(hour, minute)
                },
                description = uiState.task.description,
                onDescriptionChange = {
                    viewModel.onDescriptionChange(it)
                },
                priority = uiState.task.priority,
                onPrioritySelected = {
                    viewModel.onPriorityChange(it)
                },
                status = uiState.task.status,
                onStatusSelected = {
                    viewModel.onStatusChange(it)
                }
            )
        }
    )

    if (showDialogDeleteConfirmation.value) {
        ShowAlertDialog(
            title = stringResource(id = R.string.delete_task_title_dialog),
            description = stringResource(id = R.string.delete_task_description_dialog),
            onConfirmClick = {
                viewModel.onDeleteNote()
                showDialogDeleteConfirmation.value = !showDialogDeleteConfirmation.value
            },
            onDismissClick = {
                showDialogDeleteConfirmation.value = !showDialogDeleteConfirmation.value
            },
            onDismissRequest = {
                showDialogDeleteConfirmation.value = !showDialogDeleteConfirmation.value
            }
        )
    }
}

@Composable
private fun ShowAlertDialog(
    title: String,
    description: String,
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onBackground
            )
        },
        text = {
            Text(
                text = description,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground.copy(alpha = 0.5f)
            )
        },
        confirmButton = {
            TextButton(onClick = onConfirmClick) {
                Text(
                    text = stringResource(id = R.string.yes).uppercase(),
                    color = MaterialTheme.colors.secondary

                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissClick) {
                Text(
                    text = stringResource(id = R.string.no).uppercase(),
                    color = MaterialTheme.colors.secondary
                )
            }
        },
        onDismissRequest = onDismissRequest
    )
}
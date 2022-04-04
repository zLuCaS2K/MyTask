package com.zlucas2k.mytask.presentation.task

import android.widget.Toast
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.zlucas2k.mytask.R
import com.zlucas2k.mytask.presentation.task.common.TaskEventUI
import com.zlucas2k.mytask.presentation.task.components.TaskTopAppBar
import com.zlucas2k.mytask.presentation.task.viewmodel.TaskViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun TaskScreen(navHostController: NavHostController, viewModel: TaskViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val state = viewModel.state.value
    val isEditing = state.selectedId != 0
    val showDialogDeleteConfirmation = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        viewModel.eventUI.collectLatest { taskEvent ->
            when (taskEvent) {
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
                    Toast.makeText(context, taskEvent.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TaskTopAppBar(
                isEditing = isEditing,
                onDeleteClick = {
                    showDialogDeleteConfirmation.value = true
                },
                onSaveClick = { viewModel.onSaveNote() },
                onBackPressed = { navHostController.popBackStack() }
            )
        },
        content = {
            TaskScreenForm(
                title = state.title,
                onTitleChange = {
                    viewModel.onTitleChange(it)
                },
                date = state.date,
                onDateChange = {
                    viewModel.onDateChange(it)
                },
                time = state.time,
                onTimeChange = { hour, minute ->
                    viewModel.onTimeChange(hour, minute)
                },
                description = state.description,
                onDescriptionChange = {
                    viewModel.onDescriptionChange(it)
                },
                priority = state.priority,
                onPrioritySelected = {
                    viewModel.onPriorityChange(it)
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
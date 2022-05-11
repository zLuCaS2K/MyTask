package com.zlucas2k.mytask.presentation.screens.task.edit_task

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.zlucas2k.mytask.R
import com.zlucas2k.mytask.presentation.components.MyTaskIconButton
import com.zlucas2k.mytask.presentation.components.MyTaskTopAppBar
import com.zlucas2k.mytask.presentation.screens.task.components.form.TaskForm
import com.zlucas2k.mytask.presentation.screens.task.edit_task.utils.EditTaskScreenEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun EditTaskScreen(navController: NavController) {

    val context = LocalContext.current
    val uiState = rememberEditTaskScreenState()

    LaunchedEffect(key1 = Unit) {
        uiState.event.collectLatest { uiEvent ->
            when (uiEvent) {
                is EditTaskScreenEvent.EditTaskSuccess -> {
                    uiState.onShowSnackbarShort(context.getString(R.string.save_sucess_task))
                    navController.navigateUp()
                }

                is EditTaskScreenEvent.EditTaskFailed -> {
                    uiState.onShowSnackbarShort(uiEvent.message)
                }
            }
        }
    }

    Scaffold(
        scaffoldState = uiState.scaffoldState,
        topBar = {
            MyTaskTopAppBar(
                title = "",
                navigationIcon = {
                    MyTaskIconButton(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back),
                        onClick = { navController.popBackStack() }
                    )
                },
                actions = {
                    MyTaskIconButton(
                        imageVector = Icons.Filled.Save,
                        contentDescription = stringResource(id = R.string.save_task),
                        onClick = { uiState.onEditTask() }
                    )
                }
            )
        },
        content = {
            TaskForm(
                isEditing = true,
                taskFormState = uiState.taskFormState,
                onTimeChange = { hour, minute ->
                    uiState.applyTimeFormattingStrategy(hour, minute)
                },
                onDateChange = { date ->
                    uiState.applyDateFormattingStrategy(date)
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    )
}
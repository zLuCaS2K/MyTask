package com.zlucas2k.mytask.presentation.screens.task.add_task

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
import com.zlucas2k.mytask.presentation.screens.task.add_task.utils.AddTaskScreenEvent
import com.zlucas2k.mytask.presentation.screens.task.components.form.TaskForm
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddTaskScreen(navController: NavController) {

    val context = LocalContext.current
    val uiState = rememberAddTaskScreenState()

    LaunchedEffect(key1 = Unit) {
        uiState.event.collectLatest { uiEvent ->
            when (uiEvent) {
                is AddTaskScreenEvent.SaveTaskSuccess -> {
                    uiState.onShowSnackbarShort(context.getString(R.string.save_sucess_task))
                }

                is AddTaskScreenEvent.SaveTaskFailed -> {
                    uiState.onShowSnackbarShort(context.getString(R.string.save_sucess_task))
                }
            }
        }
    }

    Scaffold(
        scaffoldState = uiState.scaffoldState,
        topBar = {
            MyTaskTopAppBar(
                title = stringResource(id = R.string.add_task),
                navigationIcon = {
                    MyTaskIconButton(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back),
                        onClick = { navController.navigateUp() }
                    )
                },
                actions = {
                    MyTaskIconButton(
                        imageVector = Icons.Filled.Save,
                        contentDescription = stringResource(id = R.string.add_task),
                        onClick = { uiState.onSaveTask() }
                    )
                }
            )
        },
        content = {
            TaskForm(
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
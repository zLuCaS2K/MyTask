package com.zlucas2k.mytask.presentation.screens.task.add_task

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.zlucas2k.mytask.presentation.screens.task.common.AddTaskFormScreenState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class AddTaskScreenState(
    val scaffoldState: ScaffoldState,
    private val coroutineScope: CoroutineScope,
    private val addTaskViewModel: AddTaskViewModel,
) : AddTaskFormScreenState {

    override var event = addTaskViewModel.event

    override var taskFormState = addTaskViewModel.formState.value

    override fun onSaveTask() {
        addTaskViewModel.onSaveTask()
    }

    fun onShowSnackbarShort(message: String) {
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(
                message = message
            )
        }
    }

    override fun applyTimeFormattingStrategy(hour: Int, minute: Int) {
        addTaskViewModel.onTimeChange(hour, minute)
    }

    override fun applyDateFormattingStrategy(date: String) {
        addTaskViewModel.onDateChange(date)
    }
}

@Composable
fun rememberAddTaskScreenState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    addTaskViewModel: AddTaskViewModel = hiltViewModel()
) = remember {
    AddTaskScreenState(
        scaffoldState = scaffoldState,
        coroutineScope = coroutineScope,
        addTaskViewModel = addTaskViewModel
    )
}
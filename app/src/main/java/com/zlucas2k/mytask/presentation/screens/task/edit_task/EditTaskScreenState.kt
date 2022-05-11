package com.zlucas2k.mytask.presentation.screens.task.edit_task

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.zlucas2k.mytask.presentation.screens.task.common.EditTaskFormScreenState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class EditTaskScreenState(
    val scaffoldState: ScaffoldState,
    private val coroutineScope: CoroutineScope,
    private val editTaskViewModel: EditTaskViewModel,
) : EditTaskFormScreenState {

    override var event = editTaskViewModel.event

    override var taskFormState = editTaskViewModel.formState.value

    override fun onEditTask() {
        editTaskViewModel.onEditTask()
    }

    fun onShowSnackbarShort(message: String) {
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(
                message = message
            )
        }
    }

    override fun applyTimeFormattingStrategy(hour: Int, minute: Int) {
        editTaskViewModel.onTimeChange(hour, minute)
    }

    override fun applyDateFormattingStrategy(date: String) {
        editTaskViewModel.onDateChange(date)
    }
}

@Composable
fun rememberEditTaskScreenState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    editTaskViewModel: EditTaskViewModel = hiltViewModel()
) = remember {
    EditTaskScreenState(
        scaffoldState = scaffoldState,
        coroutineScope = coroutineScope,
        editTaskViewModel = editTaskViewModel
    )
}
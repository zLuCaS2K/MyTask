package com.zlucas2k.mytask.presentation.screens.task.add_task

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.zlucas2k.mytask.presentation.screens.task.add_task.utils.AddTaskScreenEvent
import com.zlucas2k.mytask.presentation.screens.task.common.ScreenState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

sealed interface AddTaskScreenState : ScreenState<AddTaskScreenEvent>

class AddTaskScreenStateImpl(
    override val scaffoldState: ScaffoldState,
    private val coroutineScope: CoroutineScope,
    private val addTaskViewModel: AddTaskViewModel
) : AddTaskScreenState {

    override val uiEvent: SharedFlow<AddTaskScreenEvent> get() = addTaskViewModel.uiEvent

    override fun showSnackbar(message: String) {
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(message = message)
        }
    }
}

@Composable
fun rememberAddTaskScreenState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    addTaskViewModel: AddTaskViewModel = hiltViewModel()
): AddTaskScreenState = remember {
    AddTaskScreenStateImpl(
        scaffoldState = scaffoldState,
        coroutineScope = coroutineScope,
        addTaskViewModel = addTaskViewModel
    )
}
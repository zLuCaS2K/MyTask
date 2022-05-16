package com.zlucas2k.mytask.presentation.screens.task.edit_task

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.zlucas2k.mytask.presentation.screens.task.common.ScreenState
import com.zlucas2k.mytask.presentation.screens.task.edit_task.utils.EditTaskScreenEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

sealed interface EditTaskScreenState : ScreenState<EditTaskScreenEvent>

class EditTaskScreenStateImpl(
    override val scaffoldState: ScaffoldState,
    private val coroutineScope: CoroutineScope,
    private val editTaskViewModel: EditTaskViewModel
) : EditTaskScreenState {

    override val uiEvent: SharedFlow<EditTaskScreenEvent> get() = editTaskViewModel.uiEvent

    override fun showSnackbar(message: String) {
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(message = message)
        }
    }
}

@Composable
fun rememberEditTaskScreenState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    editTaskViewModel: EditTaskViewModel = hiltViewModel()
): EditTaskScreenState = remember {
    EditTaskScreenStateImpl(
        scaffoldState = scaffoldState,
        coroutineScope = coroutineScope,
        editTaskViewModel = editTaskViewModel
    )
}
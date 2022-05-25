package com.zlucas2k.mytask.presentation.screens.task.add_task.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.SoftwareKeyboardController
import com.zlucas2k.mytask.presentation.screens.task.components.form.TaskForm

@Composable
@ExperimentalComposeUiApi
fun AddTaskForm(
    modifier: Modifier = Modifier,
    addTaskFormState: AddTaskFormState,
    keyboardController: SoftwareKeyboardController?
) {

    TaskForm(
        taskFormState = addTaskFormState,
        keyboardController = keyboardController,
        modifier = modifier,
    )
}
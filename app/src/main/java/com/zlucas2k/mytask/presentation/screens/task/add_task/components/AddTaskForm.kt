package com.zlucas2k.mytask.presentation.screens.task.add_task.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zlucas2k.mytask.presentation.screens.task.components.form.TaskForm

@Composable
fun AddTaskForm(
    modifier: Modifier = Modifier,
    addTaskFormState: AddTaskFormState
) {

    TaskForm(
        taskFormState = addTaskFormState,
        modifier = modifier
    )
}
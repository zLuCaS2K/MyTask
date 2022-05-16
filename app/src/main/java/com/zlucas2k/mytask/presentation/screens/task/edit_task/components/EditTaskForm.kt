package com.zlucas2k.mytask.presentation.screens.task.edit_task.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zlucas2k.mytask.presentation.screens.task.components.form.TaskForm

@Composable
fun EditTaskForm(
    modifier: Modifier = Modifier,
    editTaskFormState: EditTaskFormState
) {
    TaskForm(
        taskFormState = editTaskFormState,
        modifier = modifier
    )
}
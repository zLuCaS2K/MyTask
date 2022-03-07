package com.zlucas2k.mytask.presentation.task.components

import androidx.compose.runtime.Composable
import com.zlucas2k.mytask.domain.model.Priority

@Composable
fun TaskScreenContent(
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit,
    date: String,
    onDateChange: (String) -> Unit,
    completed: Boolean,
    onCompletedChange: (Boolean) -> Unit
) {

}
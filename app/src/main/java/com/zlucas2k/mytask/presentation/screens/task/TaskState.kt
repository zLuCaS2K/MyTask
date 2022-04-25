package com.zlucas2k.mytask.presentation.screens.task

import com.zlucas2k.mytask.presentation.common.model.TaskView

data class TaskState(
    val task: TaskView = TaskView(),
    val isEditing: Boolean = false
)
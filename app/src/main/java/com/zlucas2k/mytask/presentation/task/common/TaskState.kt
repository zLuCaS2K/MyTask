package com.zlucas2k.mytask.presentation.task.common

import com.zlucas2k.mytask.presentation.common.model.TaskView

data class TaskState(
    val task: TaskView = TaskView(),
    val isEditing: Boolean = false
)
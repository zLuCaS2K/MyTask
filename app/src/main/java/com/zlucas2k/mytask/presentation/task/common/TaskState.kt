package com.zlucas2k.mytask.presentation.task.common

import com.zlucas2k.mytask.domain.model.Priority
import com.zlucas2k.mytask.domain.model.Status

data class TaskState(
    val selectedId: Int = 0,
    val title: String = "",
    val date: String = "",
    val time: String = "",
    val description: String = "",
    val priority: Priority = Priority.NONE,
    val status: Status = Status.TODO
)

package com.zlucas2k.mytask.presentation.task.common

import com.zlucas2k.mytask.presentation.common.model.PriorityView
import com.zlucas2k.mytask.presentation.common.model.StatusView

data class TaskState(
    val selectedId: Int = 0,
    val title: String = "",
    val date: String = "",
    val time: String = "",
    val description: String = "",
    val priority: PriorityView = PriorityView.NONE,
    val status: StatusView = StatusView.TODO
)

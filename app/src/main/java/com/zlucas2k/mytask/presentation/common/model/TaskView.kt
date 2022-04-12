package com.zlucas2k.mytask.presentation.common.model

data class TaskView(
    val id: Int,
    val title: String,
    val date: String,
    val time: String,
    val description: String,
    val priority: PriorityView,
    val status: StatusView
)

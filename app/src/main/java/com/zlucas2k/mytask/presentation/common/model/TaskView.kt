package com.zlucas2k.mytask.presentation.common.model

import com.zlucas2k.mytask.common.utils.emptyString

// TODO: Mudar o tipo do id para Long
data class TaskView(
    val id: Int = 0,
    val title: String = emptyString(),
    val date: String = emptyString(),
    val time: String = emptyString(),
    val description: String = emptyString(),
    val priority: PriorityView = PriorityView.NONE,
    val status: StatusView = StatusView.TODO
)

package com.zlucas2k.mytask.presentation.common.model

data class TaskView(
    var id: Int = 0,
    var title: String = "",
    var date: String = "",
    var time: String = "",
    var description: String = "",
    var priority: PriorityView = PriorityView.NONE,
    var status: StatusView = StatusView.TODO
)

package com.zlucas2k.mytask.presentation.screens.home

import com.zlucas2k.mytask.presentation.common.model.TaskView

data class HomeScreenState(
    val tasks: List<TaskView> = emptyList(),
    val taskDetailSheet: TaskView = TaskView()
)
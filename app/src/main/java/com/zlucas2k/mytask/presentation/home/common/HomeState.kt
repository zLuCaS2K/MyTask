package com.zlucas2k.mytask.presentation.home.common

import com.zlucas2k.mytask.presentation.common.model.TaskView

data class HomeState(
    val tasks: List<TaskView> = emptyList(),
    val isSearching: Boolean = false
)
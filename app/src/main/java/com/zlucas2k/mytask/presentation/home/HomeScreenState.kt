package com.zlucas2k.mytask.presentation.home

import com.zlucas2k.mytask.domain.model.Task

data class HomeScreenState(
    val tasks: List<Task> = emptyList(),
    val anError: Boolean = false
)

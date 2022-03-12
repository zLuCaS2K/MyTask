package com.zlucas2k.mytask.presentation.home.common

import com.zlucas2k.mytask.domain.model.Task

data class HomeState(val tasks: List<Task> = emptyList())

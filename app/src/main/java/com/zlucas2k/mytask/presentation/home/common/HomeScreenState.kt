package com.zlucas2k.mytask.presentation.home.common

import com.zlucas2k.mytask.domain.util.TaskFilter
import com.zlucas2k.mytask.presentation.common.model.TaskView
import com.zlucas2k.mytask.presentation.home.common.filter.FilterWidgetState
import com.zlucas2k.mytask.presentation.home.common.search.SearchWidgetState

data class HomeScreenState(
    val tasks: List<TaskView> = emptyList(),
    val tasksCached: List<TaskView> = emptyList(),
    val isSearching: Boolean = false,
    val searchQuery: String = "",
    val searchWidgetState: SearchWidgetState = SearchWidgetState.CLOSED,
    val filterQuery: TaskFilter = TaskFilter.All,
    val filterLastQuery: TaskFilter = TaskFilter.All,
    val filterWidgetState: FilterWidgetState = FilterWidgetState.CLOSED,
)
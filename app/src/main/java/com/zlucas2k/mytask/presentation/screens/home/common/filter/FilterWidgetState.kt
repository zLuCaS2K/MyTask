package com.zlucas2k.mytask.presentation.screens.home.common.filter

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.zlucas2k.mytask.domain.util.TaskFilter
import com.zlucas2k.mytask.presentation.components.widget.WidgetState
import com.zlucas2k.mytask.presentation.components.widget.WidgetValue
import com.zlucas2k.mytask.presentation.screens.home.HomeViewModel

private sealed interface FilterWidgetState : WidgetState {
    var filterQuery: TaskFilter
}

class FilterWidgetStateImpl(
    private val homeViewModel: HomeViewModel
) : FilterWidgetState {

    override var filterQuery: TaskFilter by mutableStateOf(TaskFilter.All)
    override var currentWidgetValue: WidgetValue by mutableStateOf(WidgetValue.Closed)

    fun onFilterQueryChange(query: TaskFilter) {
        filterQuery = query
        homeViewModel.onFilterQueryChange(query)
    }

    fun onFilterWidgetStateChange() {
        currentWidgetValue = when (currentWidgetValue) {
            WidgetValue.Opened -> WidgetValue.Closed
            WidgetValue.Closed -> WidgetValue.Opened
        }
    }
}


@Composable
fun rememberFilterWidgetState(
    homeViewModel: HomeViewModel = hiltViewModel()
) = remember {
    FilterWidgetStateImpl(homeViewModel = homeViewModel)
}

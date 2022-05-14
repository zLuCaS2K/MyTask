package com.zlucas2k.mytask.presentation.screens.home.common.filter

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.zlucas2k.mytask.domain.util.TaskFilter
import com.zlucas2k.mytask.presentation.components.widget.WidgetState
import com.zlucas2k.mytask.presentation.components.widget.WidgetValue
import com.zlucas2k.mytask.presentation.screens.home.HomeViewModel

private sealed interface FilterWidgetState : WidgetState {
    var filterQuery: TaskFilter
    fun onFilterQueryChange(query: TaskFilter)
}

class FilterWidgetStateImpl(
    private val homeViewModel: HomeViewModel
) : FilterWidgetState {

    override var filterQuery: TaskFilter by mutableStateOf(TaskFilter.All)
    override val isVisible: Boolean get() = _currentWidgetValueState.value != WidgetValue.Closed

    private val _lastFilterQuery: MutableState<TaskFilter> = mutableStateOf(TaskFilter.All)
    private val _currentWidgetValueState: MutableState<WidgetValue> = mutableStateOf(WidgetValue.Closed)

    override fun onFilterQueryChange(query: TaskFilter) {
        filterQuery = query
        if (query != _lastFilterQuery) {
            _lastFilterQuery.value = query
            homeViewModel.onFilterQueryChange(query)
        }
    }

    override fun openWidget() {
        _currentWidgetValueState.value = WidgetValue.Opened
    }

    override fun closeWidget() {
        _currentWidgetValueState.value = WidgetValue.Closed
    }
}


@Composable
fun rememberFilterWidgetState(
    homeViewModel: HomeViewModel = hiltViewModel()
) = remember {
    FilterWidgetStateImpl(homeViewModel = homeViewModel)
}

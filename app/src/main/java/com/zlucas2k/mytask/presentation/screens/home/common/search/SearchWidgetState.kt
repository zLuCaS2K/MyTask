package com.zlucas2k.mytask.presentation.screens.home.common.search

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.zlucas2k.mytask.common.utils.emptyString
import com.zlucas2k.mytask.presentation.components.widget.WidgetState
import com.zlucas2k.mytask.presentation.components.widget.WidgetValue
import com.zlucas2k.mytask.presentation.screens.home.HomeViewModel

private sealed interface SearchWidgetState : WidgetState {
    var searchQuery: String
    fun onSearchQueryChange(query: String)
}

class SearchWidgetStateImpl(
    private val homeViewModel: HomeViewModel
) : SearchWidgetState {

    override var searchQuery: String by mutableStateOf(emptyString())
    override val isVisible: Boolean get() = _currentWidgetValueState.value != WidgetValue.Closed

    private val _currentWidgetValueState: MutableState<WidgetValue> = mutableStateOf(WidgetValue.Closed)

    override fun onSearchQueryChange(query: String) {
        searchQuery = query
        homeViewModel.onSearchQueryChange(query)
    }

    override fun openWidget() {
        _currentWidgetValueState.value = WidgetValue.Opened
    }

    override fun closeWidget() {
        _currentWidgetValueState.value = WidgetValue.Closed
    }
}

@Composable
fun rememberSearchWidgetState(
    homeViewModel: HomeViewModel = hiltViewModel()
) = remember {
    SearchWidgetStateImpl(homeViewModel = homeViewModel)
}
package com.zlucas2k.mytask.presentation.screens.home.common.search

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.zlucas2k.mytask.common.utils.emptyString
import com.zlucas2k.mytask.presentation.components.widget.WidgetState
import com.zlucas2k.mytask.presentation.components.widget.WidgetValue
import com.zlucas2k.mytask.presentation.screens.home.HomeViewModel

private sealed interface SearchWidgetState : WidgetState {
    var searchQuery: String
}

class SearchWidgetStateImpl(
    private val homeViewModel: HomeViewModel
) : SearchWidgetState {

    override var searchQuery: String by mutableStateOf(emptyString())
    override var currentWidgetValue: WidgetValue by mutableStateOf(WidgetValue.Closed)

    fun onSearchQueryChange(query: String) {
        searchQuery = query
        homeViewModel.onSearchQueryChange(query)
    }

    fun onSearchWidgetStateChange() {
        currentWidgetValue = when (currentWidgetValue) {
            WidgetValue.Opened -> WidgetValue.Closed
            WidgetValue.Closed -> WidgetValue.Opened
        }
    }
}

@Composable
fun rememberSearchWidgetState(
    homeViewModel: HomeViewModel = hiltViewModel()
) = remember {
    SearchWidgetStateImpl(homeViewModel = homeViewModel)
}
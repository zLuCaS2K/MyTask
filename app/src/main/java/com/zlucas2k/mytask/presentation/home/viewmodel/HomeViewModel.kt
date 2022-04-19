package com.zlucas2k.mytask.presentation.home.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zlucas2k.mytask.domain.mappers.mapToView
import com.zlucas2k.mytask.domain.usecases.task.get_all.GetAllTaskUseCase
import com.zlucas2k.mytask.presentation.home.common.HomeScreenState
import com.zlucas2k.mytask.presentation.home.common.filter.TaskStatusFilter
import com.zlucas2k.mytask.presentation.home.common.filter.TaskStatusFilterWidgetState
import com.zlucas2k.mytask.presentation.home.common.search.SearchWidgetState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllTaskUseCase: GetAllTaskUseCase
) : ViewModel() {

    private val _uiState: MutableState<HomeScreenState> = mutableStateOf(HomeScreenState())
    val uiState: State<HomeScreenState> get() = _uiState

    init {
        getAllTasks()
    }

    private fun getAllTasks() {
        viewModelScope.launch {
            getAllTaskUseCase().map { tasksNotMapped ->
                tasksNotMapped.map { taskNotMapped ->
                    taskNotMapped.mapToView()
                }
            }.collect { tasks ->
                _uiState.value = _uiState.value.copy(tasks = tasks)
                _uiState.value = _uiState.value.copy(tasksCached = tasks)
            }
        }
    }

    fun onSearchTask() {
        val searchQuery = _uiState.value.searchQuery

        if (searchQuery.isEmpty()) {
            val tasksCached = _uiState.value.tasksCached
            _uiState.value = _uiState.value.copy(tasks = tasksCached)
        } else {
            val searchResult = _uiState.value.tasksCached.filter {
                it.title.contains(searchQuery, true) || it.description.contains(searchQuery, true)
            }

            _uiState.value = _uiState.value.copy(tasks = searchResult)
        }
    }

    fun onSearchTextChange(newValue: String) {
        _uiState.value = _uiState.value.copy(searchQuery = newValue)
    }

    fun onSearchWidgetStateChange() {
        val newState = when (_uiState.value.searchWidgetState) {
            SearchWidgetState.OPENED -> SearchWidgetState.CLOSED
            SearchWidgetState.CLOSED -> SearchWidgetState.OPENED
        }

        _uiState.value = _uiState.value.copy(searchWidgetState = newState)
    }

    fun onFilterTask() {
        val filterQuery = _uiState.value.filterQuery

        if (filterQuery != TaskStatusFilter.ALL) {
            val filterResult = _uiState.value.tasksCached.filter {
                // -1 because TaskStatusFilter enum have more one property
                it.status.ordinal == filterQuery.ordinal - 1
            }

            _uiState.value = _uiState.value.copy(tasks = filterResult)
        } else {
            val tasksCached = _uiState.value.tasksCached
            _uiState.value = _uiState.value.copy(tasks = tasksCached)
        }
    }

    fun onFilterOptionChange(newValue: TaskStatusFilter) {
        _uiState.value = _uiState.value.copy(filterQuery = newValue)
    }

    fun onFilterWidgetStateChange() {
        val newState = when (_uiState.value.filterWidgetState) {
            TaskStatusFilterWidgetState.OPENED -> TaskStatusFilterWidgetState.CLOSED
            TaskStatusFilterWidgetState.CLOSED -> TaskStatusFilterWidgetState.OPENED
        }

        _uiState.value = _uiState.value.copy(filterWidgetState = newState)
    }

    fun onSearchingState(newValue: Boolean) {
        _uiState.value = _uiState.value.copy(isSearching = newValue)
    }
}
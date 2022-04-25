package com.zlucas2k.mytask.presentation.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zlucas2k.mytask.domain.mappers.mapToView
import com.zlucas2k.mytask.domain.usecases.task.filter.FilterTaskUseCase
import com.zlucas2k.mytask.domain.usecases.task.get_all.GetAllTaskUseCase
import com.zlucas2k.mytask.domain.usecases.task.search.SearchTaskUseCase
import com.zlucas2k.mytask.domain.util.TaskFilter
import com.zlucas2k.mytask.presentation.common.model.TaskView
import com.zlucas2k.mytask.presentation.screens.home.common.filter.FilterWidgetState
import com.zlucas2k.mytask.presentation.screens.home.common.search.SearchWidgetState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllTaskUseCase: GetAllTaskUseCase,
    private val searchTaskUseCase: SearchTaskUseCase,
    private val filterTaskUseCase: FilterTaskUseCase
) : ViewModel() {

    private val _uiState: MutableState<HomeScreenState> = mutableStateOf(HomeScreenState())
    val uiState: State<HomeScreenState> get() = _uiState

    private val _tasksCached: MutableState<List<TaskView>> = mutableStateOf(emptyList())
    private val _filterLastQuery: MutableState<TaskFilter> = mutableStateOf(TaskFilter.All)

    private var _searchJob: Job? = null
    private var _filterJob: Job? = null

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
                _tasksCached.value = tasks
                _uiState.value = _uiState.value.copy(tasks = tasks)
            }
        }
    }

    fun onSearchTask() {
        val searchQuery = _uiState.value.searchQuery

        if (searchQuery.isEmpty()) {
            _uiState.value = _uiState.value.copy(tasks = _tasksCached.value)
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                _searchJob?.cancel()
                _searchJob = searchTaskUseCase(searchQuery)
                    .map { tasksModel ->
                        tasksModel.map { it.mapToView() }
                    }
                    .onEach { searchResult ->
                        _uiState.value = _uiState.value.copy(tasks = searchResult)
                    }.launchIn(viewModelScope)
            }
        }
    }

    fun onSearchTextChange(newSearchQuery: String) {
        _uiState.value = _uiState.value.copy(searchQuery = newSearchQuery)
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

        if (_uiState.value.filterQuery != _filterLastQuery) {
            viewModelScope.launch(Dispatchers.IO) {
                _filterJob?.cancel()
                _filterJob = filterTaskUseCase(filterQuery)
                    .map { tasksModel ->
                        tasksModel.map { it.mapToView() }
                    }
                    .onEach { filterResult ->
                        _filterLastQuery.value = filterQuery
                        _uiState.value = _uiState.value.copy(tasks = filterResult)
                    }
                    .launchIn(viewModelScope)
            }
        }
    }

    fun onFilterOptionChange(newFilterQuery: TaskFilter) {
        _uiState.value = _uiState.value.copy(filterQuery = newFilterQuery)
    }

    fun onFilterWidgetStateChange() {
        val newState = when (_uiState.value.filterWidgetState) {
            FilterWidgetState.OPENED -> FilterWidgetState.CLOSED
            FilterWidgetState.CLOSED -> FilterWidgetState.OPENED
        }

        _uiState.value = _uiState.value.copy(filterWidgetState = newState)
    }
}
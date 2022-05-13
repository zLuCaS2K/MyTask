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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
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

    private val _tasksCache: MutableState<List<TaskView>> = mutableStateOf(emptyList())
    private val _lastFilterQuery: MutableState<TaskFilter> = mutableStateOf(TaskFilter.All)

    private var _searchJob: Job? = null
    private var _filterJob: Job? = null

    init {
        viewModelScope.launch {
            getAllTaskUseCase().map { tasksModel ->
                tasksModel.map { taskModel -> taskModel.mapToView() }
            }.collect { tasks ->
                _tasksCache.value = tasks
                _uiState.value = _uiState.value.copy(tasks = tasks)
            }
        }
    }

    fun onSearchQueryChange(searchQuery: String) {
        if (searchQuery.isBlank()) {
            _uiState.value = _uiState.value.copy(tasks = _tasksCache.value)
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                _searchJob?.cancel()
                _searchJob = searchTaskUseCase(searchQuery).map { tasksModel ->
                    tasksModel.map { taskModel -> taskModel.mapToView() }
                }.onEach { searchResult ->
                    _uiState.value = _uiState.value.copy(tasks = searchResult)
                }.launchIn(viewModelScope)
            }
        }
    }

    fun onFilterQueryChange(filterQuery: TaskFilter) {
        if (filterQuery != _lastFilterQuery) {
            viewModelScope.launch(Dispatchers.IO) {
                _searchJob?.cancel()
                _searchJob = filterTaskUseCase(filterQuery).map { tasksModel ->
                    tasksModel.map { taskModel -> taskModel.mapToView() }
                }.onEach { filterResult ->
                    _lastFilterQuery.value = filterQuery
                    _uiState.value = _uiState.value.copy(tasks = filterResult)
                }.launchIn(viewModelScope)
            }
        }
    }

    override fun onCleared() {
        _searchJob?.cancel()
        _filterJob?.cancel()
        super.onCleared()
    }
}
package com.zlucas2k.mytask.presentation.home.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zlucas2k.mytask.domain.usecases.task.get_all.GetAllTaskUseCase
import com.zlucas2k.mytask.presentation.common.model.TaskView
import com.zlucas2k.mytask.presentation.common.model.mapper.TaskViewMapperImpl
import com.zlucas2k.mytask.presentation.home.common.HomeState
import com.zlucas2k.mytask.presentation.home.common.SearchWidgetState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllTaskUseCase: GetAllTaskUseCase
) : ViewModel() {

    private val _uiState: MutableState<HomeState> = mutableStateOf(HomeState())
    val uiState: State<HomeState> get() = _uiState

    private val _searchWidgetState: MutableState<SearchWidgetState> = mutableStateOf(SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> get() = _searchWidgetState

    private val _searchTextState: MutableState<String> = mutableStateOf("")
    val searchTextState: State<String> get() = _searchTextState

    private val _presentationMapper = TaskViewMapperImpl
    private val _tasksCached: MutableState<List<TaskView>> = mutableStateOf(emptyList())

    init {
        getAllTasks()
    }

    private fun getAllTasks() {
        viewModelScope.launch {
            getAllTaskUseCase().map { tasksNotMapped ->
                tasksNotMapped.map { taskNotMapped ->
                    _presentationMapper.mapTo(taskNotMapped)
                }
            }.collect { tasks ->
                _uiState.value = _uiState.value.copy(tasks = tasks)
                _tasksCached.value = _uiState.value.tasks
            }
        }
    }

    fun onSearchTask(query: String) {
        _uiState.value = _uiState.value.copy(isSearching = true)
        if (query.isEmpty()) {
            _uiState.value = _uiState.value.copy(tasks = _tasksCached.value)
        } else {
            val searchResult = _uiState.value.tasks.filter {
                it.title.contains(query, false) || it.description.contains(query, false)
            }
            _uiState.value = _uiState.value.copy(tasks = searchResult)
        }
        _uiState.value = _uiState.value.copy(isSearching = false)
    }

    fun onSearchingState(newValue: Boolean) {
        _uiState.value = _uiState.value.copy(isSearching = newValue)
    }

    fun onTextSearchChange(newValue: String) {
        _searchTextState.value = newValue
    }

    fun onSearchWidgetStateChange(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }
}
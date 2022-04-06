package com.zlucas2k.mytask.presentation.home.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zlucas2k.mytask.domain.usecases.task.get_all.GetAllTaskUseCase
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

    private val _mapper = TaskViewMapperImpl

    private val _state: MutableState<HomeState> = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        getAllTasks()
    }

    private fun getAllTasks() {
        viewModelScope.launch {
            getAllTaskUseCase()
                .map { tasks ->
                    tasks.map { task ->
                        _mapper.mapTo(task)
                    }
                }
                .collect { tasks ->
                    _state.value = _state.value.copy(tasks = tasks)
                }
        }
    }

    private val _searchWidgetState: MutableState<SearchWidgetState> = mutableStateOf(SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableState<String> = mutableStateOf("")
    val searchTextState: State<String> = _searchTextState

    fun onSearchTask() {
        val query = _searchTextState.value
        Log.i("HomeViewModel", "Pesquisa: $query")
    }

    fun onSearchWidgetStateChange(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun onTextSearchChange(newValue: String) {
        _searchTextState.value = newValue
    }
}
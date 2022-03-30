package com.zlucas2k.mytask.presentation.home.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zlucas2k.mytask.domain.usecases.task.GetAllTaskUseCase
import com.zlucas2k.mytask.presentation.common.model.mapper.TaskViewMapperImpl
import com.zlucas2k.mytask.presentation.home.common.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllTaskUseCase: GetAllTaskUseCase
) : ViewModel() {

    private val _state: MutableState<HomeState> = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    private val _mapper = TaskViewMapperImpl

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
}
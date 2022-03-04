package com.zlucas2k.mytask.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.domain.usecases.DeleteTaskUseCase
import com.zlucas2k.mytask.domain.usecases.GetAllTaskUseCase
import com.zlucas2k.mytask.domain.usecases.SaveTaskUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeViewModel constructor(
    private val getAllTaskUseCase: GetAllTaskUseCase,
    private val saveTaskUseCase: SaveTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {

    private val _state = mutableStateOf(HomeScreenState())
    val state: State<HomeScreenState> = _state

    init {
        getAllTask()
    }

    private fun getAllTask() {
        getAllTaskUseCase().onEach { tasks ->
            _state.value = _state.value.copy(tasks = tasks, anError = false)
        }.launchIn(viewModelScope)
    }

    suspend fun saveTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            saveTaskUseCase(task)
        }
    }

    suspend fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteTaskUseCase(task)
        }
    }
}
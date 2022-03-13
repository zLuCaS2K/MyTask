package com.zlucas2k.mytask.presentation.task.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zlucas2k.mytask.domain.model.Priority
import com.zlucas2k.mytask.domain.model.Status
import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.domain.usecases.DeleteTaskUseCase
import com.zlucas2k.mytask.domain.usecases.GetTaskByIdUseCase
import com.zlucas2k.mytask.domain.usecases.SaveTaskUseCase
import com.zlucas2k.mytask.presentation.task.common.TaskState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val saveTaskUseCase: SaveTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val getTaskByIdUseCase: GetTaskByIdUseCase
) : ViewModel() {

    private val _state: MutableState<TaskState> = mutableStateOf(TaskState())
    val state: State<TaskState> = _state

    init {
        savedStateHandle.get<Int>("id")?.let { taskId ->
            if (taskId != -1) {
                viewModelScope.launch {
                    getTaskByIdUseCase(taskId)?.also { task ->
                        _state.value = TaskState(
                            selectedId = task.id,
                            title = task.title,
                            date = task.date,
                            time = task.time,
                            description = task.description,
                            priority = task.priority,
                            status = task.status
                        )
                    }
                }
            }
        }
    }

    fun onSaveNote() {
        viewModelScope.launch(Dispatchers.IO) {
            val task = Task(
                id = _state.value.selectedId,
                title = _state.value.title,
                date = _state.value.date,
                time = _state.value.time,
                description = _state.value.description,
                priority = _state.value.priority,
                status = _state.value.status
            )
            saveTaskUseCase(task)
        }
    }

    fun onDeleteNote() {
        viewModelScope.launch(Dispatchers.IO) {
            val task = Task(
                id = _state.value.selectedId,
                title = _state.value.title,
                date = _state.value.date,
                time = _state.value.time,
                description = _state.value.description,
                priority = _state.value.priority,
                status = _state.value.status
            )
            deleteTaskUseCase(task)
        }
    }

    fun onTitleChange(newText: String) {
        _state.value = _state.value.copy(title = newText)
    }

    fun onDateChange(newText: String) {
        _state.value = _state.value.copy(date = newText)
    }

    fun onTimeChange(newText: String) {
        _state.value = _state.value.copy(time = newText)
    }

    fun onDescriptionChange(newText: String) {
        _state.value = _state.value.copy(description = newText)
    }

    fun onPriorityChange(newPriority: Priority) {
        _state.value = _state.value.copy(priority = newPriority)
    }

    fun onStatusChange(newStatus: Status) {
        _state.value = _state.value.copy(status = newStatus)
    }
}
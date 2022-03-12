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
import com.zlucas2k.mytask.domain.usecases.GetTaskByIdUseCase
import com.zlucas2k.mytask.domain.usecases.SaveTaskUseCase
import com.zlucas2k.mytask.presentation.task.common.TaskState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val saveTaskUseCase: SaveTaskUseCase,
    private val getTaskByIdUseCase: GetTaskByIdUseCase,
) : ViewModel() {

    private val _state: MutableState<TaskState> = mutableStateOf(TaskState())
    val state: State<TaskState> = _state

    private val _selectId = mutableStateOf(-1)
    private val _title = mutableStateOf("")
    private val _date = mutableStateOf("")
    private val _time = mutableStateOf("")
    private val _description = mutableStateOf("")
    private val _priority = mutableStateOf(Priority.NONE)
    private val _status = mutableStateOf(Status.TODO)

    init {
        savedStateHandle.get<Int>("id")?.let { taskId ->
            if (taskId != -1) {
                viewModelScope.launch {
                    getTaskByIdUseCase(taskId)?.also { task ->
                        _selectId.value = task.id
                        _title.value = task.title
                        _date.value = task.date
                        _time.value = task.time
                        _description.value = task.description
                        _priority.value = task.priority
                        _status.value = task.status
                    }
                }
            }
        }
    }

    fun onSaveNote() {
        viewModelScope.launch(Dispatchers.IO) {
            val task = Task(
                id = _selectId.value,
                title = _title.value,
                date = _date.value,
                time = _time.value,
                description = _description.value,
                priority = _priority.value,
                status = _status.value
            )
            saveTaskUseCase(task)
        }
    }

    fun onTitleChange(newText: String) {
        _title.value = newText
    }

    fun onDateChange(newText: String) {
        _date.value = newText
    }

    fun onTimeChange(newText: String) {
        _time.value = newText
    }

    fun onDescriptionChange(newText: String) {
        _description.value = newText
    }

    fun onPriorityChange(newPriority: Priority) {
        _priority.value = newPriority
    }

    fun onStatusChange(newStatus: Status) {
        _status.value = newStatus
    }
}
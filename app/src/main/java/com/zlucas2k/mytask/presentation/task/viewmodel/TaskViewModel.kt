package com.zlucas2k.mytask.presentation.task.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zlucas2k.mytask.common.exceptions.TaskException
import com.zlucas2k.mytask.domain.usecases.DeleteTaskUseCase
import com.zlucas2k.mytask.domain.usecases.GetTaskByIdUseCase
import com.zlucas2k.mytask.domain.usecases.SaveTaskUseCase
import com.zlucas2k.mytask.presentation.common.model.PriorityView
import com.zlucas2k.mytask.presentation.common.model.StatusView
import com.zlucas2k.mytask.presentation.common.model.TaskView
import com.zlucas2k.mytask.presentation.common.model.mapper.PriorityViewMapperImpl
import com.zlucas2k.mytask.presentation.common.model.mapper.StatusViewMapperImpl
import com.zlucas2k.mytask.presentation.common.model.mapper.TaskViewMapperImpl
import com.zlucas2k.mytask.presentation.task.common.TaskEventUI
import com.zlucas2k.mytask.presentation.task.common.TaskState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
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

    private val _eventUI = MutableSharedFlow<TaskEventUI>()
    val eventUI: SharedFlow<TaskEventUI> = _eventUI

    private val _mapper = TaskViewMapperImpl

    init {
        savedStateHandle.get<Int>("id")?.let { taskId ->
            if (taskId != 0) {
                viewModelScope.launch {
                    getTaskByIdUseCase(taskId)?.also { task ->
                        _state.value = TaskState(
                            selectedId = task.id,
                            title = task.title,
                            date = task.date,
                            time = task.time,
                            description = task.description,
                            priority = PriorityViewMapperImpl.mapTo(task.priority),
                            status = StatusViewMapperImpl.mapTo(task.status)
                        )
                    }
                }
            } else {
                _state.value = TaskState(selectedId = taskId)
            }
        }
    }

    fun onSaveNote() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val task = TaskView(
                    id = _state.value.selectedId,
                    title = _state.value.title,
                    date = _state.value.date,
                    time = _state.value.time,
                    description = _state.value.description,
                    priority = _state.value.priority,
                    status = _state.value.status
                )

                saveTaskUseCase(_mapper.mapFrom(task))
                _eventUI.emit(TaskEventUI.SaveTask)
            } catch (e: TaskException) {
                _eventUI.emit(
                    TaskEventUI.ShowToast(e.message ?: "Não foi possivel salvar")
                )
            }
        }
    }

    fun onDeleteNote() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val task = TaskView(
                    id = _state.value.selectedId,
                    title = _state.value.title,
                    date = _state.value.date,
                    time = _state.value.time,
                    description = _state.value.description,
                    priority = _state.value.priority,
                    status = _state.value.status
                )

                deleteTaskUseCase(_mapper.mapFrom(task))
                _eventUI.emit(TaskEventUI.DeleteTask)
            } catch (e: TaskException) {
                _eventUI.emit(
                    TaskEventUI.ShowToast(e.message ?: "Não foi possivel deletar")
                )
            }
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

    fun onPriorityChange(newPriority: PriorityView) {
        _state.value = _state.value.copy(priority = newPriority)
    }

    fun onStatusChange(newStatus: StatusView) {
        _state.value = _state.value.copy(status = newStatus)
    }
}
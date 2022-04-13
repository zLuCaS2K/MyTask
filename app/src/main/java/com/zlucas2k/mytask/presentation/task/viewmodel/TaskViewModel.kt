package com.zlucas2k.mytask.presentation.task.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zlucas2k.mytask.common.exceptions.TaskException
import com.zlucas2k.mytask.domain.mappers.mapToView
import com.zlucas2k.mytask.domain.usecases.format.date.FormatDateUseCase
import com.zlucas2k.mytask.domain.usecases.format.time.FormatTimeUseCase
import com.zlucas2k.mytask.domain.usecases.task.delete.DeleteTaskUseCase
import com.zlucas2k.mytask.domain.usecases.task.get.GetTaskByIdUseCase
import com.zlucas2k.mytask.domain.usecases.task.save.SaveTaskUseCase
import com.zlucas2k.mytask.presentation.common.model.PriorityView
import com.zlucas2k.mytask.presentation.common.model.StatusView
import com.zlucas2k.mytask.presentation.common.model.TaskView
import com.zlucas2k.mytask.presentation.common.model.mapper.mapToModel
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
    private val getTaskByIdUseCase: GetTaskByIdUseCase,
    private val formatDateUseCase: FormatDateUseCase,
    private val formatTimeUseCase: FormatTimeUseCase
) : ViewModel() {

    private val _state: MutableState<TaskState> = mutableStateOf(TaskState(0))
    val state: State<TaskState> = _state

    private val _eventUI = MutableSharedFlow<TaskEventUI>()
    val eventUI: SharedFlow<TaskEventUI> = _eventUI

    init {
        savedStateHandle.get<Int>("id")?.let { taskId ->
            if (taskId != 0) {
                viewModelScope.launch {
                    getTaskByIdUseCase(taskId)?.also { task ->
                        _state.value = TaskState(
                            id = task.id,
                            title = task.title,
                            date = task.date,
                            time = task.time,
                            description = task.description,
                            priority = task.priority.mapToView(),
                            status = task.status.mapToView()
                        )
                    }
                }
            } else {
                _state.value = TaskState(id = taskId)
            }
        }
    }

    fun onSaveNote() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val taskView = TaskView(
                    id = _state.value.id,
                    title = _state.value.title,
                    date = _state.value.date,
                    time = _state.value.time,
                    description = _state.value.description,
                    priority = _state.value.priority,
                    status = _state.value.status
                )

                saveTaskUseCase(taskView.mapToModel())
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
                val taskView = TaskView(
                    id = _state.value.id,
                    title = _state.value.title,
                    date = _state.value.date,
                    time = _state.value.time,
                    description = _state.value.description,
                    priority = _state.value.priority,
                    status = _state.value.status
                )

                deleteTaskUseCase(taskView.mapToModel())
                _eventUI.emit(TaskEventUI.DeleteTask)
            } catch (e: TaskException) {
                _eventUI.emit(
                    TaskEventUI.ShowToast(e.message ?: "Não foi possivel deletar")
                )
            }
        }
    }

    fun onTitleChange(newTitle: String) {
        _state.value = _state.value.copy(title = newTitle)
    }

    fun onDateChange(newDate: String) {
        val dateFormatted = formatDateUseCase(newDate)
        _state.value = _state.value.copy(date = dateFormatted)
    }

    fun onTimeChange(hour: Int, minute: Int) {
        val timeFormatted = formatTimeUseCase(hour, minute)
        _state.value = _state.value.copy(time = timeFormatted)
    }

    fun onDescriptionChange(newDescription: String) {
        _state.value = _state.value.copy(description = newDescription)
    }

    fun onPriorityChange(newPriority: PriorityView) {
        _state.value = _state.value.copy(priority = newPriority)
    }

    fun onStatusChange(newStatus: StatusView) {
        _state.value = _state.value.copy(status = newStatus)
    }
}
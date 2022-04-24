package com.zlucas2k.mytask.presentation.screens.task.viewmodel

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
import com.zlucas2k.mytask.presentation.screens.task.common.TaskEventUI
import com.zlucas2k.mytask.presentation.screens.task.common.TaskState
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

    private val _uiState: MutableState<TaskState> = mutableStateOf(TaskState())
    val uiState: State<TaskState> get() = _uiState

    private val _uiEvent = MutableSharedFlow<TaskEventUI>()
    val uiEvent: SharedFlow<TaskEventUI> get() = _uiEvent

    init {
        handleTaskEditing()
    }

    private fun handleTaskEditing() {
        savedStateHandle.get<Int>("id")?.let { taskId ->
            if (taskId != 0) {
                viewModelScope.launch {
                    getTaskByIdUseCase(taskId)?.also { taskEditing ->
                        _uiState.value =
                            uiState.value.copy(task = taskEditing.mapToView(), isEditing = true)
                    }
                }
            } else {
                val taskNotEditing = TaskView(id = taskId)
                _uiState.value = _uiState.value.copy(task = taskNotEditing, isEditing = false)
            }
        }
    }

    fun onSaveNote() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val task = _uiState.value.task

                saveTaskUseCase(task.mapToModel())
                _uiEvent.emit(TaskEventUI.SaveTask)
            } catch (e: TaskException) {
                _uiEvent.emit(
                    TaskEventUI.ShowToast(e.message ?: "Não foi possivel salvar")
                )
            }
        }
    }

    fun onDeleteNote() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val task = _uiState.value.task

                deleteTaskUseCase(task.mapToModel())
                _uiEvent.emit(TaskEventUI.DeleteTask)
            } catch (e: TaskException) {
                _uiEvent.emit(
                    TaskEventUI.ShowToast(e.message ?: "Não foi possivel deletar")
                )
            }
        }
    }

    fun onTitleChange(newTitle: String) {
        _uiState.value = _uiState.value.copy(task = _uiState.value.task.copy(title = newTitle))
    }

    fun onDateChange(newDate: String) {
        val dateFormatted = formatDateUseCase(newDate)
        _uiState.value = _uiState.value.copy(task = _uiState.value.task.copy(date = dateFormatted))
    }

    fun onTimeChange(hour: Int, minute: Int) {
        val timeFormatted = formatTimeUseCase(hour, minute)
        _uiState.value = _uiState.value.copy(task = _uiState.value.task.copy(time = timeFormatted))
    }

    fun onDescriptionChange(newDescription: String) {
        _uiState.value =
            _uiState.value.copy(task = _uiState.value.task.copy(description = newDescription))
    }

    fun onPriorityChange(newPriority: PriorityView) {
        _uiState.value =
            _uiState.value.copy(task = _uiState.value.task.copy(priority = newPriority))
    }

    fun onStatusChange(newStatus: StatusView) {
        _uiState.value = _uiState.value.copy(task = _uiState.value.task.copy(status = newStatus))
    }
}
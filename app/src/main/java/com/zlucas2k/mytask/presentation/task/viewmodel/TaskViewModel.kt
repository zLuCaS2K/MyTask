package com.zlucas2k.mytask.presentation.task.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zlucas2k.mytask.common.exceptions.TaskException
import com.zlucas2k.mytask.domain.usecases.format.date.FormatDateUseCase
import com.zlucas2k.mytask.domain.usecases.format.time.FormatTimeUseCase
import com.zlucas2k.mytask.domain.usecases.task.delete.DeleteTaskUseCase
import com.zlucas2k.mytask.domain.usecases.task.get.GetTaskByIdUseCase
import com.zlucas2k.mytask.domain.usecases.task.save.SaveTaskUseCase
import com.zlucas2k.mytask.presentation.common.model.PriorityView
import com.zlucas2k.mytask.presentation.common.model.TaskView
import com.zlucas2k.mytask.presentation.common.model.mapper.PriorityViewMapperImpl
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
    private val getTaskByIdUseCase: GetTaskByIdUseCase,
    private val formatDateUseCase: FormatDateUseCase,
    private val formatTimeUseCase: FormatTimeUseCase
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
                            priority = PriorityViewMapperImpl.mapTo(task.priority)
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
                val taskMapped = _mapper.mapFrom(
                    TaskView(
                        id = _state.value.selectedId,
                        title = _state.value.title,
                        date = _state.value.date,
                        time = _state.value.time,
                        description = _state.value.description,
                        priority = _state.value.priority
                    )
                )

                saveTaskUseCase(taskMapped)
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
                val taskMapped = _mapper.mapFrom(
                    TaskView(
                        id = _state.value.selectedId,
                        title = _state.value.title,
                        date = _state.value.date,
                        time = _state.value.time,
                        description = _state.value.description,
                        priority = _state.value.priority
                    )
                )

                deleteTaskUseCase(taskMapped)
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
        val dateFormatted = formatDateUseCase(newText)
        _state.value = _state.value.copy(date = dateFormatted)
    }

    fun onTimeChange(hour: Int, minute: Int) {
        val timeFormatted = formatTimeUseCase(hour, minute)
        _state.value = _state.value.copy(time = timeFormatted)
    }

    fun onDescriptionChange(newText: String) {
        _state.value = _state.value.copy(description = newText)
    }

    fun onPriorityChange(newPriority: PriorityView) {
        _state.value = _state.value.copy(priority = newPriority)
    }
}
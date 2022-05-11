package com.zlucas2k.mytask.presentation.screens.task.edit_task

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
import com.zlucas2k.mytask.domain.usecases.task.edit.EditTaskUseCase
import com.zlucas2k.mytask.domain.usecases.task.get.GetTaskByIdUseCase
import com.zlucas2k.mytask.presentation.common.model.TaskView
import com.zlucas2k.mytask.presentation.common.model.mapper.mapToModel
import com.zlucas2k.mytask.presentation.screens.task.components.form.TaskFormState
import com.zlucas2k.mytask.presentation.screens.task.components.form.TaskFormStateImpl
import com.zlucas2k.mytask.presentation.screens.task.edit_task.utils.EditTaskScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditTaskViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val formatTimeUseCase: FormatTimeUseCase,
    private val formatDateUseCase: FormatDateUseCase,
    private val editTaskUseCase: EditTaskUseCase,
    private val getTaskByIdUseCase: GetTaskByIdUseCase,
) : ViewModel() {

    private val _formState: MutableState<TaskFormState> = mutableStateOf(TaskFormStateImpl())
    val formState: State<TaskFormState> get() = _formState

    private val _event: MutableSharedFlow<EditTaskScreenEvent> = MutableSharedFlow()
    val event: SharedFlow<EditTaskScreenEvent> get() = _event

    private var _idTask: Int = 0

    init {
        savedStateHandle.get<Int>("id")?.let { taskId ->
            if (taskId != 0) {
                viewModelScope.launch {
                    getTaskByIdUseCase(taskId)?.also { task ->
                        _idTask = task.id
                        setTaskInTaskFormState(task.mapToView())
                    }
                }
            }
        }
    }

    fun onEditTask() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val task = buildTask()
                editTaskUseCase(task.mapToModel())
                _event.emit(EditTaskScreenEvent.EditTaskSuccess)
            } catch (e: TaskException) {
                _event.emit(EditTaskScreenEvent.EditTaskFailed(e.message ?: ""))
            }
        }
    }

    fun onTimeChange(newHour: Int, newMinute: Int) {
        val newTimeFormatted = formatTimeUseCase(newHour, newMinute)
        _formState.value.time = newTimeFormatted
    }

    fun onDateChange(newDate: String) {
        val newDateFormatted = formatDateUseCase(newDate)
        _formState.value.date = newDateFormatted
    }

    private fun buildTask(): TaskView {
        return TaskView(
            id = _idTask,
            title = _formState.value.title,
            time = _formState.value.time,
            date = _formState.value.date,
            description = _formState.value.description,
            priority = _formState.value.priority,
            status = _formState.value.status
        )
    }

    private fun setTaskInTaskFormState(taskView: TaskView) {
        _formState.value.title = taskView.title
        _formState.value.time = taskView.time
        _formState.value.date = taskView.date
        _formState.value.description = taskView.description
        _formState.value.priority = taskView.priority
        _formState.value.status = taskView.status
    }
}
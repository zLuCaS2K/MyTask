package com.zlucas2k.mytask.presentation.screens.task.add_task

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zlucas2k.mytask.common.exceptions.TaskException
import com.zlucas2k.mytask.domain.usecases.format.date.FormatDateUseCase
import com.zlucas2k.mytask.domain.usecases.format.time.FormatTimeUseCase
import com.zlucas2k.mytask.domain.usecases.task.save.SaveTaskUseCase
import com.zlucas2k.mytask.presentation.common.model.TaskView
import com.zlucas2k.mytask.presentation.common.model.mapper.mapToModel
import com.zlucas2k.mytask.presentation.screens.task.add_task.utils.AddTaskScreenEvent
import com.zlucas2k.mytask.presentation.screens.task.components.form.TaskFormState
import com.zlucas2k.mytask.presentation.screens.task.components.form.TaskFormStateImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val saveTaskUseCase: SaveTaskUseCase,
    private val formatTimeUseCase: FormatTimeUseCase,
    private val formatDateUseCase: FormatDateUseCase,
) : ViewModel() {

    private val _formState: MutableState<TaskFormState> = mutableStateOf(TaskFormStateImpl())
    val formState: State<TaskFormState> get() = _formState

    private val _event: MutableSharedFlow<AddTaskScreenEvent> = MutableSharedFlow()
    val event: SharedFlow<AddTaskScreenEvent> get() = _event

    fun onSaveTask() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val task = buildTask()
                saveTaskUseCase(task.mapToModel())
                _event.emit(AddTaskScreenEvent.SaveTaskSuccess)
            } catch (e: TaskException) {
                _event.emit(AddTaskScreenEvent.SaveTaskFailed(e.message ?: ""))
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
            title = _formState.value.title,
            time = _formState.value.time,
            date = _formState.value.date,
            description = _formState.value.description,
            priority = _formState.value.priority,
            status = _formState.value.status
        )
    }
}
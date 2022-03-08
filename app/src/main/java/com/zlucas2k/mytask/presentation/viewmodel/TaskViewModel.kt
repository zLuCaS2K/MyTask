package com.zlucas2k.mytask.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zlucas2k.mytask.common.utils.Action
import com.zlucas2k.mytask.common.utils.RequestState
import com.zlucas2k.mytask.domain.model.Priority
import com.zlucas2k.mytask.domain.model.Status
import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.domain.usecases.DeleteTaskUseCase
import com.zlucas2k.mytask.domain.usecases.GetAllTaskUseCase
import com.zlucas2k.mytask.domain.usecases.SaveTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val getAllTaskUseCase: GetAllTaskUseCase,
    private val saveTaskUseCase: SaveTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {

    val action: MutableState<Action> = mutableStateOf(Action.NO_ACTION)
    val idTask: MutableState<Int> = mutableStateOf(0)
    val titleTask: MutableState<String> = mutableStateOf("")
    val dateTask: MutableState<String> = mutableStateOf("")
    val timeTask: MutableState<String> = mutableStateOf("")
    val descriptionTask: MutableState<String> = mutableStateOf("")
    val priorityTask: MutableState<Priority> = mutableStateOf(Priority.LOW)
    val statusTask: MutableState<Status> = mutableStateOf(Status.TODO)

    private val _allTasks = MutableStateFlow<RequestState<List<Task>>>(RequestState.Idle)
    val allTasks: StateFlow<RequestState<List<Task>>> = _allTasks

    fun handleDatabaseActions(action: Action) {
        when (action) {
            Action.ADD -> saveTask()
            Action.UPDATE -> saveTask()
            Action.DELETE -> deleteTask()
            else -> {}
        }
        this.action.value = Action.NO_ACTION
    }

    fun getAllTasks() {
        _allTasks.value = RequestState.Loading
        try {
            viewModelScope.launch(Dispatchers.IO) {
                getAllTaskUseCase().collect {
                    _allTasks.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception) {
            _allTasks.value = RequestState.Error(e)
        }
    }

    private fun saveTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val task = Task(
                title = titleTask.value,
                date = dateTask.value,
                time = timeTask.value,
                description = descriptionTask.value,
                priority = priorityTask.value,
                status = statusTask.value
            )
            saveTaskUseCase(task)
        }
    }

    private fun deleteTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val task = Task(
                title = titleTask.value,
                date = dateTask.value,
                time = timeTask.value,
                description = descriptionTask.value,
                priority = priorityTask.value,
                status = statusTask.value
            )
            deleteTaskUseCase(task)
        }
    }
}
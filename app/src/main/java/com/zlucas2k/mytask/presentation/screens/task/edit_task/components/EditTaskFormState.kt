package com.zlucas2k.mytask.presentation.screens.task.edit_task.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.zlucas2k.mytask.presentation.common.model.PriorityView
import com.zlucas2k.mytask.presentation.common.model.StatusView
import com.zlucas2k.mytask.presentation.common.model.TaskView
import com.zlucas2k.mytask.presentation.screens.task.components.form.TaskFormState
import com.zlucas2k.mytask.presentation.screens.task.edit_task.EditTaskViewModel

sealed interface EditTaskFormState : TaskFormState {
    fun onEditTask()
    fun onStatusChange(status: StatusView)
}

class EditTaskFormStateImpl(
    private val editTaskViewModel: EditTaskViewModel
) : EditTaskFormState {

    override val task: TaskView get() = editTaskViewModel.task.value

    override fun onEditTask() {
        editTaskViewModel.onEditTask()
    }

    override fun onTitleChange(title: String) {
        editTaskViewModel.onTitleChange(title)
    }

    override fun onTimeChange(hour: Int, minute: Int) {
        editTaskViewModel.onTimeChange(hour, minute)
    }

    override fun onDateChange(date: String) {
        editTaskViewModel.onDateChange(date)
    }

    override fun onDescriptionChange(description: String) {
        editTaskViewModel.onDescriptionChange(description)
    }

    override fun onPriorityChange(priority: PriorityView) {
        editTaskViewModel.onPriorityChange(priority)
    }

    override fun onStatusChange(status: StatusView) {
        editTaskViewModel.onStatusChange(status)
    }
}

@Composable
fun rememberEditTaskFormState(
    editTaskViewModel: EditTaskViewModel = hiltViewModel()
): EditTaskFormState = remember {
    EditTaskFormStateImpl(
        editTaskViewModel = editTaskViewModel
    )
}
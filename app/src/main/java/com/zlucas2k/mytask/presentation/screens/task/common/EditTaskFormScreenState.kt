package com.zlucas2k.mytask.presentation.screens.task.common

import com.zlucas2k.mytask.presentation.screens.task.edit_task.utils.EditTaskScreenEvent
import kotlinx.coroutines.flow.SharedFlow

interface EditTaskFormScreenState : FormScreenState {

    var event: SharedFlow<EditTaskScreenEvent>

    fun onEditTask()
}
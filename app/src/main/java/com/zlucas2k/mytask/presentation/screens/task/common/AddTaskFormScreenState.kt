package com.zlucas2k.mytask.presentation.screens.task.common

import com.zlucas2k.mytask.presentation.screens.task.add_task.utils.AddTaskScreenEvent
import kotlinx.coroutines.flow.SharedFlow

interface AddTaskFormScreenState : FormScreenState {

    var event: SharedFlow<AddTaskScreenEvent>

    fun onSaveTask()
}
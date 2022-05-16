package com.zlucas2k.mytask.presentation.screens.task.components.form

import com.zlucas2k.mytask.presentation.common.model.PriorityView
import com.zlucas2k.mytask.presentation.common.model.TaskView

interface TaskFormState {
    val task: TaskView

    fun onTitleChange(title: String)
    fun onTimeChange(hour: Int, minute: Int)
    fun onDateChange(date: String)
    fun onDescriptionChange(description: String)
    fun onPriorityChange(priority: PriorityView)
}
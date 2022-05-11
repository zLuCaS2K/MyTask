package com.zlucas2k.mytask.presentation.screens.task.common

import androidx.compose.runtime.Stable
import com.zlucas2k.mytask.presentation.screens.task.components.form.TaskFormState

@Stable
sealed interface FormScreenState {

    var taskFormState: TaskFormState

    fun applyTimeFormattingStrategy(hour: Int, minute: Int)

    fun applyDateFormattingStrategy(date: String)
}
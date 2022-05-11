package com.zlucas2k.mytask.presentation.screens.task.components.form

import androidx.compose.runtime.Stable
import com.zlucas2k.mytask.presentation.common.model.StatusView
import com.zlucas2k.mytask.presentation.common.model.PriorityView

@Stable
interface TaskFormState {
    var title: String
    var time: String
    var date: String
    var description: String
    var priority: PriorityView
    var status: StatusView
}
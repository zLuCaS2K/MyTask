package com.zlucas2k.mytask.presentation.screens.task.components.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.zlucas2k.mytask.common.utils.emptyString
import com.zlucas2k.mytask.presentation.common.model.PriorityView
import com.zlucas2k.mytask.presentation.common.model.StatusView

class TaskFormStateImpl : TaskFormState {

    override var title: String by mutableStateOf(emptyString())

    override var time: String by mutableStateOf(emptyString())

    override var date: String by mutableStateOf(emptyString())

    override var description: String by mutableStateOf(emptyString())

    override var priority: PriorityView by mutableStateOf(PriorityView.NONE)

    override var status: StatusView by mutableStateOf(StatusView.TODO)
}
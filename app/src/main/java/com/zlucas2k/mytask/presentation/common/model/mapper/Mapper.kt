package com.zlucas2k.mytask.presentation.common.model.mapper

import com.zlucas2k.mytask.domain.model.Priority
import com.zlucas2k.mytask.domain.model.Status
import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.presentation.common.model.PriorityView
import com.zlucas2k.mytask.presentation.common.model.StatusView
import com.zlucas2k.mytask.presentation.common.model.TaskView

fun TaskView.mapToModel() = Task(
    id,
    title,
    date,
    time,
    description,
    priority.mapToModel(),
    status.mapToModel()
)

fun PriorityView.mapToModel() = when (this) {
    PriorityView.NONE -> Priority.NONE
    PriorityView.LOW -> Priority.LOW
    PriorityView.MEDIUM -> Priority.MEDIUM
    PriorityView.HIGH -> Priority.HIGH
}

fun StatusView.mapToModel() = when (this) {
    StatusView.TODO -> Status.TODO
    StatusView.PROGRESS -> Status.PROGRESS
    StatusView.DONE -> Status.DONE
}
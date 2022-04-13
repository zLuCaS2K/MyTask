package com.zlucas2k.mytask.domain.mappers

import com.zlucas2k.mytask.data.entity.PriorityDTO
import com.zlucas2k.mytask.data.entity.StatusDTO
import com.zlucas2k.mytask.data.entity.TaskEntity
import com.zlucas2k.mytask.domain.model.Priority
import com.zlucas2k.mytask.domain.model.Status
import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.presentation.common.model.PriorityView
import com.zlucas2k.mytask.presentation.common.model.StatusView
import com.zlucas2k.mytask.presentation.common.model.TaskView

fun Task.mapToEntity() = TaskEntity(
    id,
    title,
    date,
    time,
    description,
    priority.mapToEntity(),
    status.mapToEntity()
)

fun Priority.mapToEntity() = when (this) {
    Priority.NONE -> PriorityDTO.NONE
    Priority.LOW -> PriorityDTO.LOW
    Priority.MEDIUM -> PriorityDTO.MEDIUM
    Priority.HIGH -> PriorityDTO.HIGH
}

fun Status.mapToEntity() = when (this) {
    Status.TODO -> StatusDTO.TODO
    Status.PROGRESS -> StatusDTO.PROGRESS
    Status.DONE -> StatusDTO.DONE
}

fun Task.mapToView() = TaskView(
    id,
    title,
    date,
    time,
    description,
    priority.mapToView(),
    status.mapToView()
)

fun Priority.mapToView() = when (this) {
    Priority.NONE -> PriorityView.NONE
    Priority.LOW -> PriorityView.LOW
    Priority.MEDIUM -> PriorityView.MEDIUM
    Priority.HIGH -> PriorityView.HIGH
}

fun Status.mapToView() = when (this) {
    Status.TODO -> StatusView.TODO
    Status.PROGRESS -> StatusView.PROGRESS
    Status.DONE -> StatusView.DONE
}
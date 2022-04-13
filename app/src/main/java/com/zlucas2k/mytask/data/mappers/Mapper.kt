package com.zlucas2k.mytask.data.mappers

import com.zlucas2k.mytask.data.entity.PriorityDTO
import com.zlucas2k.mytask.data.entity.StatusDTO
import com.zlucas2k.mytask.data.entity.TaskEntity
import com.zlucas2k.mytask.domain.model.Priority
import com.zlucas2k.mytask.domain.model.Status
import com.zlucas2k.mytask.domain.model.Task

fun TaskEntity.mapFromModel() = Task(
    id,
    title,
    date,
    time,
    description,
    priority.mapFromModel(),
    status.mapFromModel()
)

fun PriorityDTO.mapFromModel() = when (this) {
    PriorityDTO.NONE -> Priority.NONE
    PriorityDTO.LOW -> Priority.LOW
    PriorityDTO.MEDIUM -> Priority.MEDIUM
    PriorityDTO.HIGH -> Priority.HIGH
}

fun StatusDTO.mapFromModel() = when (this) {
    StatusDTO.TODO -> Status.TODO
    StatusDTO.PROGRESS -> Status.PROGRESS
    StatusDTO.DONE -> Status.DONE
}
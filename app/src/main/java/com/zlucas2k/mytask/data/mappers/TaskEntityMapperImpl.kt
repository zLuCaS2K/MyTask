package com.zlucas2k.mytask.data.mappers

import com.zlucas2k.mytask.common.mapper.Mapper
import com.zlucas2k.mytask.data.entity.TaskEntity
import com.zlucas2k.mytask.domain.model.Task

object TaskEntityMapperImpl : Mapper<Task, TaskEntity> {

    override fun mapTo(modelEntry: Task): TaskEntity {
        return TaskEntity(
            id = modelEntry.id,
            title = modelEntry.title,
            date = modelEntry.date,
            time = modelEntry.time,
            description = modelEntry.description,
            priority = PriorityDTOMapperImpl.mapTo(modelEntry.priority),
            status = StatusDTOMapperImpl.mapTo(modelEntry.status)
        )
    }

    override fun mapFrom(modelEntry: TaskEntity): Task {
        return Task(
            id = modelEntry.id,
            title = modelEntry.title,
            date = modelEntry.date,
            time = modelEntry.time,
            description = modelEntry.description,
            priority = PriorityDTOMapperImpl.mapFrom(modelEntry.priority),
            status = StatusDTOMapperImpl.mapFrom(modelEntry.status)
        )
    }
}
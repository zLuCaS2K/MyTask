package com.zlucas2k.mytask.common.mapper

import com.zlucas2k.mytask.data.entity.TaskEntity
import com.zlucas2k.mytask.domain.model.Task

object TaskMapperImpl : Mapper<TaskEntity, Task> {

    override fun mapEntityToModel(entity: TaskEntity): Task {
        return Task(
            id = entity.id,
            title = entity.title,
            date = entity.date,
            time = entity.time,
            description = entity.description,
            priority = entity.priority,
            status = entity.status
        )
    }

    override fun mapModelToEntity(model: Task): TaskEntity {
        return TaskEntity(
            id = model.id,
            title = model.title,
            date = model.date,
            time = model.time,
            description = model.description,
            priority = model.priority,
            status = model.status
        )
    }
}
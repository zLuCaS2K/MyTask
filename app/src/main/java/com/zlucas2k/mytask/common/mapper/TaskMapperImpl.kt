package com.zlucas2k.mytask.common.mapper

import com.zlucas2k.mytask.data.entity.TaskEntity
import com.zlucas2k.mytask.domain.model.Task

object TaskMapperImpl : Mapper<TaskEntity, Task> {

    override fun mapEntityToModel(entity: TaskEntity): Task {
        return Task(
            id = entity.id,
            title = entity.title,
            description = entity.description,
            priority = entity.priority,
            date = entity.date,
            isCompleted = entity.isCompleted
        )
    }

    override fun mapModelToEntity(model: Task): TaskEntity {
        return TaskEntity(
            id = model.id,
            title = model.title,
            description = model.description,
            priority = model.priority,
            date = model.date,
            isCompleted = model.isCompleted
        )
    }
}
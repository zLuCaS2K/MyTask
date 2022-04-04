package com.zlucas2k.mytask.presentation.common.model.mapper

import com.zlucas2k.mytask.common.mapper.Mapper
import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.presentation.common.model.TaskView

object TaskViewMapperImpl : Mapper<Task, TaskView> {

    override fun mapTo(modelEntry: Task): TaskView {
        return TaskView(
            id = modelEntry.id,
            title = modelEntry.title,
            date = modelEntry.date,
            time = modelEntry.time,
            description = modelEntry.description,
            priority = PriorityViewMapperImpl.mapTo(modelEntry.priority)
        )
    }

    override fun mapFrom(modelEntry: TaskView): Task {
        return Task(
            id = modelEntry.id,
            title = modelEntry.title,
            date = modelEntry.date,
            time = modelEntry.time,
            description = modelEntry.description,
            priority = PriorityViewMapperImpl.mapFrom(modelEntry.priority)
        )
    }
}
package com.zlucas2k.mytask.domain.usecases.task.edit

import com.zlucas2k.mytask.common.exceptions.TaskException
import com.zlucas2k.mytask.domain.model.Status
import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.domain.repository.TaskRepository
import com.zlucas2k.mytask.domain.usecases.shedule.cancel.CancelScheduleTaskUseCase
import com.zlucas2k.mytask.domain.usecases.shedule.shedule.ScheduleTaskUseCase

class EditTaskUseCaseImpl constructor(
    private val repository: TaskRepository,
    private val scheduleTaskUseCase: ScheduleTaskUseCase,
    private val cancelScheduleTaskUseCase: CancelScheduleTaskUseCase,
) : EditTaskUseCase {

    override suspend fun invoke(task: Task) {
        if (task.title.isBlank() || task.description.isBlank() || task.date.isBlank() || task.time.isBlank()) {
            throw TaskException("Preencha todos os campos!")
        }

        val delayWork = task.getScheduleDelayInMillis()

        if (task.status == Status.TODO) {
            if (delayWork < 0) {
                throw TaskException("Insira uma data válida!")
            }
        }

        repository.updateTask(task)
        cancelScheduleTaskUseCase(task = task)

        if (task.status == Status.TODO) {
            scheduleTaskUseCase(task = task, delayInMillis = delayWork)
        }
    }
}
package com.zlucas2k.mytask.domain.usecases.task.edit

import com.zlucas2k.mytask.domain.model.Status
import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.domain.repository.TaskRepository
import com.zlucas2k.mytask.domain.usecases.shedule.cancel.CancelScheduleTaskUseCase
import com.zlucas2k.mytask.domain.usecases.shedule.shedule.ScheduleTaskUseCase
import com.zlucas2k.mytask.domain.usecases.validate.input.ValidateInputsUseCase
import com.zlucas2k.mytask.domain.usecases.validate.shedule.ValidateScheduleTimeUseCase

class EditTaskUseCaseImpl constructor(
    private val repository: TaskRepository,
    private val scheduleTaskUseCase: ScheduleTaskUseCase,
    private val cancelScheduleTaskUseCase: CancelScheduleTaskUseCase,
    private val validateInputsUseCase: ValidateInputsUseCase,
    private val validateScheduleTimeUseCase: ValidateScheduleTimeUseCase
) : EditTaskUseCase {

    override suspend fun invoke(task: Task) {
        validateInputsUseCase(task.title, task.description, task.date, task.time)

        if (task.status == Status.TODO) {
            validateScheduleTimeUseCase(task.time, task.date, task.status).also { delayInMillisTaskWork ->
                scheduleTaskUseCase(task = task, delayInMillis = delayInMillisTaskWork)
            }
        }

        repository.updateTask(task)
        cancelScheduleTaskUseCase(task = task)
    }
}
package com.zlucas2k.mytask.domain.usecases.task.save

import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.domain.repository.TaskRepository
import com.zlucas2k.mytask.domain.usecases.shedule.cancel.CancelScheduleTaskUseCase
import com.zlucas2k.mytask.domain.usecases.shedule.shedule.ScheduleTaskUseCase
import com.zlucas2k.mytask.domain.usecases.validate.input.ValidateInputsUseCase
import com.zlucas2k.mytask.domain.usecases.validate.shedule.ValidateScheduleTimeUseCase
import javax.inject.Inject

class SaveTaskUseCaseImpl @Inject constructor(
    private val repository: TaskRepository,
    private val scheduleTaskUseCase: ScheduleTaskUseCase,
    private val cancelScheduleTaskUseCase: CancelScheduleTaskUseCase,
    private val validateInputsUseCase: ValidateInputsUseCase,
    private val validateScheduleTimeUseCase: ValidateScheduleTimeUseCase
) : SaveTaskUseCase {

    override suspend operator fun invoke(task: Task) {
        validateInputsUseCase(task.title, task.description, task.date, task.time)

        validateScheduleTimeUseCase(task.time, task.date, task.status).also { delayInMillisTaskWork ->

            repository.saveTask(task).also { idTask ->
                val taskSchedule = task.copy(id = idTask.toInt())

                cancelScheduleTaskUseCase(task = taskSchedule)
                scheduleTaskUseCase(task = taskSchedule, delayInMillis = delayInMillisTaskWork)
            }
        }
    }
}
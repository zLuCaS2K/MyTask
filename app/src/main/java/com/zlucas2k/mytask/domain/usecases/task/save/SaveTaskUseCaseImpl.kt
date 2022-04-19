package com.zlucas2k.mytask.domain.usecases.task.save

import com.zlucas2k.mytask.common.exceptions.TaskException
import com.zlucas2k.mytask.domain.model.Status
import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.domain.repository.TaskRepository
import com.zlucas2k.mytask.domain.usecases.shedule.cancel.CancelScheduleTaskUseCase
import com.zlucas2k.mytask.domain.usecases.shedule.shedule.ScheduleTaskUseCase
import javax.inject.Inject

class SaveTaskUseCaseImpl @Inject constructor(
    private val repository: TaskRepository,
    private val scheduleTaskUseCase: ScheduleTaskUseCase,
    private val cancelScheduleTaskUseCase: CancelScheduleTaskUseCase
) : SaveTaskUseCase {

    override suspend operator fun invoke(task: Task) {
        if (task.title.isBlank() || task.description.isBlank() || task.date.isBlank() || task.time.isBlank()) {
            throw TaskException("Preencha todos os campos!")
        }

        val delay = task.getScheduleDelayInMillis()
        if (delay < 0) {
            throw TaskException("Insira uma data válida!")
        }

        val id = repository.saveTask(task)
        val taskSheduleModel = task.copy(id = id.toInt())

        cancelScheduleTaskUseCase(taskSheduleModel)

        if (task.status == Status.TODO) {
            scheduleTaskUseCase(taskSheduleModel, delay)
        }
    }
}
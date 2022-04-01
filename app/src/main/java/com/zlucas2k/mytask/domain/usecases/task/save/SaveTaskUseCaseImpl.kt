package com.zlucas2k.mytask.domain.usecases.task.save

import com.zlucas2k.mytask.common.exceptions.TaskException
import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.domain.repository.TaskRepository
import com.zlucas2k.mytask.domain.usecases.shedule.shedule.SheduleTaskUseCase
import javax.inject.Inject

class SaveTaskUseCaseImpl @Inject constructor(
    private val repository: TaskRepository,
    private val sheduleTaskUseCase: SheduleTaskUseCase
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
        sheduleTaskUseCase(task.copy(id = id.toInt()), delay)
    }
}
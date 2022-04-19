package com.zlucas2k.mytask.domain.usecases.task.delete

import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.domain.repository.TaskRepository
import com.zlucas2k.mytask.domain.usecases.shedule.cancel.CancelScheduleTaskUseCase
import javax.inject.Inject

class DeleteTaskUseCaseImpl @Inject constructor(
    private val repository: TaskRepository,
    private val cancelScheduleTaskUseCase: CancelScheduleTaskUseCase
) : DeleteTaskUseCase {

    override suspend operator fun invoke(task: Task) {
        repository.deleteTask(task)
        cancelScheduleTaskUseCase(task)
    }
}
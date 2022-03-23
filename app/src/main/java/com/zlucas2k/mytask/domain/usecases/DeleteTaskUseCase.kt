package com.zlucas2k.mytask.domain.usecases

import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.domain.repository.TaskRepository
import com.zlucas2k.mytask.infrastructure.worker.provider.TaskWorkerProvider
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val repository: TaskRepository,
    private val workerProvider: TaskWorkerProvider
) {

    suspend operator fun invoke(task: Task) {
        repository.deleteTask(task)
        workerProvider.cancelTaskWork(task)
    }
}
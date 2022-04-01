package com.zlucas2k.mytask.domain.usecases.shedule.shedule

import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.infrastructure.worker.provider.WorkerProvider
import javax.inject.Inject

class SheduleTaskUseCaseImpl @Inject constructor(
    private val workerProvider: WorkerProvider<Task>
) : SheduleTaskUseCase {

    override operator fun invoke(task: Task, delay: Long) {
        workerProvider.createWork(task, delay)
    }
}
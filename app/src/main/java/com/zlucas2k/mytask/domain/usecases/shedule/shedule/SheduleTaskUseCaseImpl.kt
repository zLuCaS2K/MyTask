package com.zlucas2k.mytask.domain.usecases.shedule.shedule

import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.infrastructure.worker.provider.TaskWorkerProvider
import javax.inject.Inject

class SheduleTaskUseCaseImpl @Inject constructor(
    private val workerProvider: TaskWorkerProvider
) : SheduleTaskUseCase {

    override operator fun invoke(task: Task) {
        workerProvider.createTaskWork(task)
    }
}
package com.zlucas2k.mytask.domain.usecases.shedule.cancel

import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.infrastructure.worker.provider.TaskWorkerProvider
import javax.inject.Inject

class CancelSheduleTaskUseCaseImpl @Inject constructor(
    private val workerProvider: TaskWorkerProvider
) : CancelSheduleTaskUseCase {

    override operator fun invoke(task: Task) {
        workerProvider.cancelTaskWork(task)
    }
}
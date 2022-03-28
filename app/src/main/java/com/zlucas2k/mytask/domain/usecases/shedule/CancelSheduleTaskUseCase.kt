package com.zlucas2k.mytask.domain.usecases.shedule

import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.infrastructure.worker.provider.TaskWorkerProvider
import javax.inject.Inject

class CancelSheduleTaskUseCase @Inject constructor(
    private val workerProvider: TaskWorkerProvider
) {

    operator fun invoke(data: Task) {
        workerProvider.cancelTaskWork(data)
    }
}
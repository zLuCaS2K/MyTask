package com.zlucas2k.mytask.domain.usecases.shedule.cancel

import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.infrastructure.worker.provider.WorkerProvider
import javax.inject.Inject

class CancelSheduleTaskUseCaseImpl @Inject constructor(
    private val workerProvider: WorkerProvider<Task>
) : CancelSheduleTaskUseCase {

    override operator fun invoke(task: Task) {
        workerProvider.cancelWork(task)
    }
}
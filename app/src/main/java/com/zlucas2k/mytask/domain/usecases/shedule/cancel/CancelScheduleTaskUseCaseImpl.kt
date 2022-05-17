package com.zlucas2k.mytask.domain.usecases.shedule.cancel

import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.domain.providers.WorkerProvider
import javax.inject.Inject

class CancelScheduleTaskUseCaseImpl @Inject constructor(
    private val workerProvider: WorkerProvider<Task>
) : CancelScheduleTaskUseCase {

    override operator fun invoke(task: Task) {
        workerProvider.cancelWork(task)
    }
}
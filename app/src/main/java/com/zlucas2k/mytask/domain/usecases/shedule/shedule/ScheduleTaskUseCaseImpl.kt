package com.zlucas2k.mytask.domain.usecases.shedule.shedule

import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.infrastructure.worker.provider.WorkerProvider
import javax.inject.Inject

class ScheduleTaskUseCaseImpl @Inject constructor(
    private val workerProvider: WorkerProvider<Task>
) : ScheduleTaskUseCase {

    override operator fun invoke(task: Task, delayInMillis: Long) {
        workerProvider.createWork(task, delayInMillis)
    }
}
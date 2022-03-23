package com.zlucas2k.mytask.infrastructure.worker.provider

import com.zlucas2k.mytask.domain.model.Task

interface TaskWorkerProvider {

    fun createTaskWork(task: Task)

    fun cancelTaskWork(task: Task)
}
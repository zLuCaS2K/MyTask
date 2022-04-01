package com.zlucas2k.mytask.domain.usecases.task.delete

import com.zlucas2k.mytask.domain.model.Task

interface DeleteTaskUseCase {

    suspend operator fun invoke(task: Task)
}
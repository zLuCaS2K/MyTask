package com.zlucas2k.mytask.domain.usecases.task.save

import com.zlucas2k.mytask.domain.model.Task

interface SaveTaskUseCase {

    suspend operator fun invoke(task: Task)
}
package com.zlucas2k.mytask.domain.usecases.task.edit

import com.zlucas2k.mytask.domain.model.Task

interface EditTaskUseCase {

    suspend operator fun invoke(task: Task)
}
package com.zlucas2k.mytask.domain.usecases.task.get

import com.zlucas2k.mytask.domain.model.Task

interface GetTaskByIdUseCase {

    suspend operator fun invoke(id: Int): Task?
}
package com.zlucas2k.mytask.domain.usecases.task

import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.domain.repository.TaskRepository
import javax.inject.Inject

class GetTaskByIdUseCase @Inject constructor(
    private val repository: TaskRepository
) {

    suspend operator fun invoke(id: Int): Task? {
        return repository.getTaskById(id)
    }
}